package task;

/**
 * Represents a general task with a description.
 */
public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task to be displayed to the user.
     * @return a string representation of the task to be displayed to the user.
     */
    @Override
    public String toString() {
        String check;
        if (this.isDone) {
            check = "[X] ";
        } else {
            check = "[ ] ";
        }
        return check + this.description;
    }
}
