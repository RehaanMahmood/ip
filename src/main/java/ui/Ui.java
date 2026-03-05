package ui;

import storage.Storage;
import task.*;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private TaskList tasks;
    private Storage storage;

    public Ui(Scanner scanner, Storage storage, TaskList tasks) {
        this.scanner = scanner;
        this.storage = storage;
        this.tasks = tasks;
    }

    public void start() {
        printHorizontalRule("");
        System.out.println(indent(5) + "$$$$$$$\\                      $$\\                                     ");
        System.out.println(indent(5) + "$$  __$$\\                     $$ |                                    ");
        System.out.println(indent(5) + "$$ |  $$ |$$\\   $$\\  $$$$$$$\\ $$$$$$$\\   $$$$$$\\   $$$$$$$\\  $$$$$$$\\ ");
        System.out.println(indent(5) + "$$ |  $$ |$$ |  $$ |$$  _____|$$  __$$\\ $$  __$$\\ $$  _____|$$  _____|");
        System.out.println(indent(5) + "$$ |  $$ |$$ |  $$ |$$ /      $$ |  $$ |$$$$$$$$ |\\$$$$$$\\  \\$$$$$$\\  ");
        System.out.println(indent(5) + "$$ |  $$ |$$ |  $$ |$$ |      $$ |  $$ |$$   ____| \\____$$\\  \\____$$\\ ");
        System.out.println(indent(5) + "$$$$$$$  |\\$$$$$$  |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$$\\ $$$$$$$  |$$$$$$$  |");
        System.out.println(indent(5) + "\\_______/  \\______/  \\_______|\\__|  \\__| \\_______|\\_______/ \\_______/ ");
        printHorizontalRule("");
        System.out.println(indent(5) + "Hey, I'm Duchess.");
        System.out.println(indent(5) + "How can I help you?");
        printHorizontalRule();

        this.storage.loadTasks(this.tasks);
    }

    public void run() {
        while (true) {
            String input = this.scanner.nextLine();
            printHorizontalRule("");
            if (input.equals("bye")) {
                System.out.println(indent(5) + "Bye, see you.");
                printHorizontalRule("");
                break;
            } else if (input.startsWith("mark ")) {
                int index = Parser.parseIndex(input);
                this.tasks.mark(index);
                System.out.println(indent(5) + "Marked this task as done:");
                System.out.println(indent(7) + this.tasks.get(index));
                printHorizontalRule();
            } else if (input.startsWith("unmark ")) {
                int index = Parser.parseIndex(input);
                this.tasks.unmark(index);
                System.out.println(indent(5) + "Unmarked this task:");
                System.out.println(indent(7) + this.tasks.get(index));
                printHorizontalRule();
            } else if (input.startsWith("delete ")) {
                int index = Parser.parseIndex(input);
                try {
                    Task failureFlag = this.tasks.get(index); // Throws exception immediately
                    System.out.println(indent(5) + "Okay, deleted this task:");
                    System.out.println(indent(7) + this.tasks.get(index));
                    this.tasks.remove(index);
                    if (this.tasks.size() == 1) {
                        System.out.println(indent(5) + "You now have 1 task.");
                    } else {
                        System.out.println(indent(5) + "You now have " + this.tasks.size() + " tasks.");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indent(5) + "You don't have any tasks.");
                }
                printHorizontalRule();
            } else if (input.equals("list")) {
                if (this.tasks.size() > 0) {
                    System.out.println(indent(5) + "You have the following tasks:");
                }
                printTasks(this.tasks);
                printHorizontalRule();
            } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                Task task;
                try {
                    task = Parser.parseTask(input);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(indent(5) + "Incorrect input.");
                    printHorizontalRule();
                    continue;
                }
                this.tasks.add(task);
                System.out.println(indent(5) + "Added new task:");
                System.out.println(indent(7) + task);
                if (this.tasks.size() == 1) {
                    System.out.println(indent(5) + "You now have 1 task.");
                } else {
                    System.out.println(indent(5) + "You now have " + this.tasks.size() + " tasks.");
                }
                printHorizontalRule();
            }  else if (input.startsWith("find ")) {
                TaskList tasksFound = this.tasks.find(Parser.parseFind(input));
                if (tasksFound.size() > 0) {
                    System.out.println(indent(5) + "The following tasks were found:");
                }
                printTasks(tasksFound);
                printHorizontalRule();
            } else {
                System.out.println(indent(5) + "Input not recognised.");
                printHorizontalRule();
            }
            this.storage.saveTasks(this.tasks);
        }
    }

    private void printTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println(indent(5) + "There no tasks.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(indent(5) + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private void printHorizontalRule(int indent, String end) {
        System.out.println(indent(indent) + "__________________________________________________________________________" + end);
    }

    private void printHorizontalRule(int indent) {
        printHorizontalRule(indent, "\n");
    }

    private void printHorizontalRule(String end) {
        printHorizontalRule(4, end);
    }

    private void printHorizontalRule() {
        printHorizontalRule(4, "\n");
    }

    public static String indent(int numSpaces) {
        String result = "";
        for (int i = 0; i < numSpaces; i++) {
            result += " ";
        }
        return result;
    }
}
