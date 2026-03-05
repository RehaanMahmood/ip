package storage;

import task.*;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handles saving tasks into a text file and loading those tasks upon startup.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the tasks in <code>tasks</code> as a list to a text file stored locally.
     * @param tasks the list of tasks.
     */
    public void saveTasks(TaskList tasks) {
        try {
            File file = new File(this.filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(taskToFileString(tasks.get(i)) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(Ui.indent(5) + "Error saving tasks.");
        }
    }

    /**
     * Converts <code>task</code> to a string format to be stored in a text file and read upon startup.
     * @param task the task to be stored.
     * @return a string representation of <code>task</code> to be stored in a text file.
     */
    private String taskToFileString(Task task) {
        String done;
        String rest;
        String taskStr = task.toString();
        done = taskStr.contains("[X]") ? "1" : "0";
        if (task instanceof ToDo) {
            rest = taskStr.substring(7).trim();
            return "T | " + done + " | " + rest;
        } else if (task instanceof Deadline) {
            String inner = taskStr.substring(7).trim();
            int byIdx = inner.lastIndexOf(" (by: ");
            String desc = inner.substring(0, byIdx);
            String by = inner.substring(byIdx + 6, inner.length() - 1);
            return "D | " + done + " | " + desc + " | " + by;
        } else {
            String inner = taskStr.substring(7).trim();
            int fromIdx = inner.lastIndexOf(" (from: ");
            String desc = inner.substring(0, fromIdx);
            String fromTo = inner.substring(fromIdx + 8, inner.length() - 1);
            int toIdx = fromTo.lastIndexOf(" to: ");
            String from = fromTo.substring(0, toIdx);
            String to = fromTo.substring(toIdx + 5);
            return "E | " + done + " | " + desc + " | " + from + " | " + to;
        }
    }

    /**
     * Reads the existing tasks stored in the text file upon startup and adds them to <code>tasks</code>.
     * @param tasks the list the existing tasks are added to.
     */
    public void loadTasks(TaskList tasks) {
        File file = new File(this.filePath);
        if (!file.exists()) {
            return;
        }
        try {
            java.util.List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            for (String line : lines) {
                try {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");
                    Task task;
                    switch (type) {
                    case "T" -> task = new ToDo(parts[2].trim());
                    case "D" -> task = new Deadline(parts[2].trim(), parts[3].trim());
                    case "E" -> task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                    default -> {
                        continue;
                    }
                    }
                    if (isDone) {
                        task.setDone();
                    }
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println(Ui.indent(5) + "Skipping corrupted line \"" + line + "\"\n");
                }
            }
        } catch (IOException e) {
            System.out.println(Ui.indent(5) + "Error loading tasks.");
        }
    }
}
