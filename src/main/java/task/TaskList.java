package task;

import java.util.ArrayList;

/**
 * A list of <code>Task</code> objects. Handles addition, deletion, finding, marking and unmarking of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds <code>task</code> to the list of tasks.
     * @param task the task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task at index <code>index</code> in the list.
     * @param index the index of the required task.
     * @return the task at index <code>index</code>.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes the task at index <code>index</code> in the list.
     * @param index the index of the task to be removed.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks the task at index <code>index</code> as done.
     * @param index the index of the task to be marked as done.
     */
    public void mark(int index) {
        this.tasks.get(index).setDone();
    }

    /**
     * Marks the task at index <code>index</code> as undone.
     * @param index the index of the task to be marked as undone.
     */
    public void unmark(int index) {
        this.tasks.get(index).setUndone();
    }

    /**
     * Returns the list of all tasks containing <code>keyword</code> in their description.
     * @param keyword the keyword to search for in the list.
     * @return the list of all tasks containing <code>keyword</code> in their description.
     */
    public TaskList find(String keyword) {
        TaskList tasksFound = new TaskList();
        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                tasksFound.add(task);

            }
        }
        return tasksFound;
    }
}
