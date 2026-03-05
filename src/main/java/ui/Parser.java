package ui;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Provides methods that return relevant information based on the user's input.
 */
public class Parser {

    public static int parseIndex(String input) {
        String[] words = input.split(" ");
        int number = Integer.parseInt(words[1]);
        return number - 1;
    }

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

    public static String parseFind(String input) {
        return input.substring(5);
    }
}
