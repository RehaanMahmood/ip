package duchess;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Duchess {
    public static final String HORIZONTAL_RULE = "__________________________________________________________________________";
    public static final String FILE_PATH = "data" + File.separator + "duchess.txt";

    public static void main(String[] args) {
        System.out.println(indent() + HORIZONTAL_RULE);
        System.out.println(indent(5) + "$$$$$$$\\                      $$\\                                     ");
        System.out.println(indent(5) + "$$  __$$\\                     $$ |                                    ");
        System.out.println(indent(5) + "$$ |  $$ |$$\\   $$\\  $$$$$$$\\ $$$$$$$\\   $$$$$$\\   $$$$$$$\\  $$$$$$$\\ ");
        System.out.println(indent(5) + "$$ |  $$ |$$ |  $$ |$$  _____|$$  __$$\\ $$  __$$\\ $$  _____|$$  _____|");
        System.out.println(indent(5) + "$$ |  $$ |$$ |  $$ |$$ /      $$ |  $$ |$$$$$$$$ |\\$$$$$$\\  \\$$$$$$\\  ");
        System.out.println(indent(5) + "$$ |  $$ |$$ |  $$ |$$ |      $$ |  $$ |$$   ____| \\____$$\\  \\____$$\\ ");
        System.out.println(indent(5) + "$$$$$$$  |\\$$$$$$  |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$$\\ $$$$$$$  |$$$$$$$  |");
        System.out.println(indent(5) + "\\_______/  \\______/  \\_______|\\__|  \\__| \\_______|\\_______/ \\_______/ ");
        System.out.println(indent() + HORIZONTAL_RULE);
        System.out.println(indent(5) + "Hey, I'm Duchess.");
        System.out.println(indent(5) + "How can I help you?");
        System.out.println(indent() + HORIZONTAL_RULE + "\n");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        loadTasks(tasks);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(indent() + HORIZONTAL_RULE);
            if (input.equals("bye")) {
                System.out.println(indent(5) + "Bye, see you.");
                System.out.println(indent() + HORIZONTAL_RULE);
                break;
            } else if (input.startsWith("mark ")) {
                String[] words = input.split(" ");
                int number = Integer.parseInt(words[1]);
                tasks.get(number - 1).setDone();
                System.out.println(indent(5) + "Marked this task as done:");
                System.out.println(indent(7) + tasks.get(number - 1));
                System.out.println(indent() + HORIZONTAL_RULE + "\n");
                saveTasks(tasks);
            } else if (input.startsWith("unmark ")) {
                String[] words = input.split(" ");
                int number = Integer.parseInt(words[1]);
                tasks.get(number - 1).setUndone();
                System.out.println(indent(5) + "Unmarked this task:");
                System.out.println(indent(7) + tasks.get(number - 1));
                System.out.println(indent() + HORIZONTAL_RULE + "\n");
            } else if (input.startsWith("delete ")) {
                String[] words = input.split(" ");
                int number = Integer.parseInt(words[1]);
                try {
                    Task failureFlag = tasks.get(number - 1); // Throws exception immediately
                    System.out.println(indent(5) + "Okay, deleted this task:");
                    System.out.println(indent(7) + tasks.get(number - 1));
                    tasks.remove(number - 1);
                    if (tasks.size() == 1) {
                        System.out.println(indent(5) + "You now have 1 task.");
                    } else {
                        System.out.println(indent(5) + "You now have " + tasks.size() + " tasks.");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indent(5) + "You don't have any tasks.");
                }
                System.out.println(indent() + HORIZONTAL_RULE + "\n");
                saveTasks(tasks);
            } else if (input.equals("list")) {
                printTasks(tasks);
                System.out.println(indent() + HORIZONTAL_RULE + "\n");
            } else { // Logic for adding tasks
                Task task;
                if (input.startsWith("todo ")) {
                    task = new ToDo(input.substring(5));
                } else if (input.startsWith("deadline ")) {
                    String[] words = input.substring(9).split(" /by ");
                    task = new Deadline(words[0], words[1]);
                } else if (input.startsWith("event ")) {
                    String[] words = input.substring(6).split(" /from ");
                    String[] fromAndTo = words[1].split(" /to ");
                    task = new Event(words[0], fromAndTo[0], fromAndTo[1]);
                } else {
                    System.out.println(indent(5) + "Input not recognised.");
                    System.out.println(indent() + HORIZONTAL_RULE + "\n");
                    continue;
                }
                tasks.add(task);
                System.out.println(indent(5) + "Added new task:");
                System.out.println(indent(7) + task);
                saveTasks(tasks);
                if (tasks.size() == 1) {
                    System.out.println(indent(5) + "You now have 1 task.");
                } else {
                    System.out.println(indent(5) + "You now have " + tasks.size() + " tasks.");
                }
                System.out.println(indent() + HORIZONTAL_RULE + "\n");
            }
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(taskToFileString(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(indent(5) + "Error saving tasks.");
        }
    }

    public static String taskToFileString(Task task) {
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

    public static void loadTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try {
            java.util.List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
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
                    System.out.println("Skipping corrupted line \"" + line + "\"");
                }
            }
        } catch (IOException e) {
            System.out.println(indent(5) + "Error loading tasks.");
        }
    }

    public static void printTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(indent(5) + "You have no tasks.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(indent(5) + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public static String indent(int numSpaces) {
        String result = "";
        for (int i = 0; i < numSpaces; i++) {
            result += " ";
        }
        return result;
    }

    public static String indent() {
        return indent(4);
    }
}