package duchess;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import storage.Storage;
import task.Task;
import ui.Ui;

public class Duchess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("data" + File.separator + "duchess.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui(scanner, storage, tasks);
        ui.run();
    }
}