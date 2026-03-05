package task;

/**
 * Represents a simple task on a to-do list.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do to be displayed to the user.
     * @return a string representation of the to-do to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
