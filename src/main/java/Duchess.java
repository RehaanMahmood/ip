import java.util.Scanner;

public class Duchess {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hey, I'm Duchess.");
        System.out.println("     How can I help you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input =  scanner.nextLine();
            System.out.println("    ____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("     Bye, see you.");
                System.out.println("    ____________________________________________________________");
                break;
            } else {
                System.out.println("     " + input);
                System.out.println("    ____________________________________________________________\n");
            }
        }
    }
}
