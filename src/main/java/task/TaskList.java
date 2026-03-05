package task;

import java.util.ArrayList;

/**
 * A list of Task objects. Handles addition, deletion, finding, marking and unmarking of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void mark(int index) {
        this.tasks.get(index).setDone();
    }

    public void unmark(int index) {
        this.tasks.get(index).setUndone();
    }

    public TaskList find(String string) {
        TaskList tasksFound = new TaskList();
        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(string.toLowerCase())) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }
}
