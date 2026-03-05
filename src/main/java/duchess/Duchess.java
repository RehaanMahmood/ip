package duchess;

import java.util.Scanner;
import java.io.File;

import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * Represents the chatbot that the user interacts with.
 */
public class Duchess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("data" + File.separator + "duchess.txt");
        TaskList tasks = new TaskList();
        Ui ui = new Ui(scanner, storage, tasks);
        ui.start();
        ui.run();
    }
}