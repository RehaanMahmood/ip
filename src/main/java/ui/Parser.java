package ui;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Provides methods that return relevant information based on the user's input.
 */
public class Parser {

    /**
     * Returns the list index (starting from 0) based on the task number provided by the user in
     * the input. The second word of <code>input</code> is expected to be the task number.
     * @param input the command given by the user.
     * @return the list index (starting from 0) based on the task number provided.
     */
    public static int parseIndex(String input) {
        String[] words = input.split(" ");
        int number = Integer.parseInt(words[1]);
        return number - 1;
    }

    /**
     * Creates and returns a <code>Task</code> object based on the user command. The first two words indicate the type
     * of task and its description respectively. The remaining words, if any, are the timing information related to the
     * task.
     * @param input the command given by the user.
     * @return the <code>Task</code> object that the user wants to add.
     * @throws ArrayIndexOutOfBoundsException if the user does not follow the command format and enter too many or too
     * few words.
     */
    public static Task parseTask(String input) throws ArrayIndexOutOfBoundsException {
        Task task = new Task("");
        if (input.startsWith("todo ")) {
            task = new ToDo(input.substring(5));
        } else if (input.startsWith("deadline ")) {
            String[] words = input.substring(9).split(" /by ");
            task = new Deadline(words[0], words[1]);
        } else if (input.startsWith("event ")) {
            String[] words = input.substring(6).split(" /from ");
            String[] fromAndTo = words[1].split(" /to ");
            task = new Event(words[0], fromAndTo[0], fromAndTo[1]);
        }
        return task;
    }

    /**
     * Returns the keyword that the user is looking for. The second word of <code>input</code> is the keyword.
     * @param input the command given by the user.
     * @return the keyword that the user is looking for.
     */
    public static String parseFind(String input) {
        return input.substring(5);
    }
}
