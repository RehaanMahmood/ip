public class Task {
    private final String DESCRIPTION;
    private boolean isDone;

    public Task(String description) {
        this.DESCRIPTION = description;
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
        return check + this.DESCRIPTION;
    }
}
