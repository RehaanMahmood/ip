import java.util.Arrays;
import java.util.Scanner;

public class Duchess {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hey, I'm Duchess.");
        System.out.println("     How can I help you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
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
                list[number - 1].setDone();
                System.out.println("     Marked this task as done:");
                System.out.println("       " + list[number - 1]);
                System.out.println("    ____________________________________________________________\n");
            } else if (input.startsWith("unmark ")) {
                String[] words = input.split(" ");
                int number = Integer.parseInt(words[1]);
                list[number - 1].setUndone();
                System.out.println("     Unmarked this task:");
                System.out.println("       " + list[number - 1]);
                System.out.println("    ____________________________________________________________\n");
            } else if (input.equals("list")) {
                printList(Arrays.copyOf(list, count));
                System.out.println("    ____________________________________________________________\n");
            } else {
                if (count >= 100) {
                    System.out.println("     Your list is full.");
                } else {
                    Task task = new Task(input);
                    list[count] = task;
                    count++;
                    System.out.println("     Added task: " + input);
                }
                System.out.println("    ____________________________________________________________\n");
            }
        }
    }

    public static void printList(Task[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println("     " + (i + 1) + ". " + list[i]);
        }
    }
}