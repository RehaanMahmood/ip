import java.util.Arrays;
import java.util.Scanner;

public class Duchess {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hey, I'm Duchess.");
        System.out.println("     How can I help you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        while (true) {
            String input = scanner.nextLine();
            System.out.println("    ____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("     Bye, see you.");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (input.startsWith("mark ")) {
                String[] words = input.split(" ");
                int number = Integer.parseInt(words[1]);
                tasks[number - 1].setDone();
                System.out.println("     Marked this task as done:");
                System.out.println("       " + tasks[number - 1]);
                System.out.println("    ____________________________________________________________\n");
            } else if (input.startsWith("unmark ")) {
                String[] words = input.split(" ");
                int number = Integer.parseInt(words[1]);
                tasks[number - 1].setUndone();
                System.out.println("     Unmarked this task:");
                System.out.println("       " + tasks[number - 1]);
                System.out.println("    ____________________________________________________________\n");
            } else if (input.equals("list")) {
                printTasks(Arrays.copyOf(tasks, count));
                System.out.println("    ____________________________________________________________\n");
            } else if (count >= 100) { // If the above conditions aren't true, then the user wants to add a task
                System.out.println("     Your list is full.");
            } else { // Logic for adding tasks
                if (input.startsWith("todo ")) {
                    ToDo todo = new ToDo(input.substring(5));
                    tasks[count] = todo;
                    System.out.println("     Added new task:");
                    System.out.println("       " + todo);
                } else if (input.startsWith("deadline ")) {
                    String[] words = input.substring(9).split(" /by ");
                    Deadline deadline = new Deadline(words[0], words[1]);
                    tasks[count] = deadline;
                    System.out.println("     Added new task:");
                    System.out.println("       " + deadline);
                } else if (input.startsWith("event ")) {
                    String[] words = input.substring(6).split(" /from ");
                    String[] fromAndTo = words[1].split(" /to ");
                    Event event = new Event(words[0], fromAndTo[0], fromAndTo[1]);
                    tasks[count] = event;
                    System.out.println("     Added new task:");
                    System.out.println("       " + event);
                } else {
                    System.out.println("     Input not recognised.");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }
                count++;
                if (count == 1) {
                    System.out.println("     You now have 1 task.");
                } else {
                    System.out.println("     You now have " + count + " tasks.");
                }
                System.out.println("    ____________________________________________________________\n");
            }
        }
    }

    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks[i]);
        }
    }
}