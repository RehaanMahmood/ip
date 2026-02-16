package duchess;

import java.util.ArrayList;
import java.util.Scanner;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Duchess {
    public static final String HORIZONTAL_RULE = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(indent() + HORIZONTAL_RULE);
        System.out.println(indent(5) + "Hey, I'm Duchess.");
        System.out.println(indent(5) + "How can I help you?");
        System.out.println(indent() + HORIZONTAL_RULE + "\n");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;
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
            } else if (input.startsWith("unmark ")) {
                String[] words = input.split(" ");
                int number = Integer.parseInt(words[1]);
                tasks.get(number - 1).setUndone();
                System.out.println(indent(5) + "Unmarked this task:");
                System.out.println(indent(7) + tasks.get(number - 1));
                System.out.println(indent() + HORIZONTAL_RULE + "\n");
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
                try {
                    tasks.add(task);
                    System.out.println(indent(5) + "Added new task:");
                    System.out.println(indent(7) + task);
                    taskCount++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indent(5) + "Your list is full.");
                }
                if (taskCount == 1) {
                    System.out.println(indent(5) + "You now have 1 task.");
                } else {
                    System.out.println(indent(5) + "You now have " + taskCount + " tasks.");
                }
                System.out.println(indent() + HORIZONTAL_RULE + "\n");
            }
        }
    }

    public static void printTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
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