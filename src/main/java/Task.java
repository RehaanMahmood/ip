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
