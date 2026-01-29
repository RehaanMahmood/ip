import java.util.Arrays;
import java.util.Scanner;

public class Duchess {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hey, I'm Duchess.");
        System.out.println("     How can I help you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int count = 0;
        while (true) {
            String input =  scanner.nextLine();
            System.out.println("    ____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("     Bye, see you.");
                System.out.println("    ____________________________________________________________");
                break;
            } else {
                list[count] = input;
                count++;
                printList(Arrays.copyOf(list, count));
                System.out.println("    ____________________________________________________________\n");
            }
        }
    }

    public static void printList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println("     " + (i + 1) + ". " + list[i]);
        }
    }
}
