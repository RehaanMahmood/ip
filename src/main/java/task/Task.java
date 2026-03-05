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

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

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
