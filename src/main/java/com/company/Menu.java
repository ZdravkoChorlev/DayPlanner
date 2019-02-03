package com.company;

import java.util.Comparator;
import java.util.Scanner;
import static java.lang.System.out;

public class Menu extends MenuManager {

    Scanner scanner = new Scanner(System.in);

    public Menu() {

    }

    public void menuOptions() {

        if (getParameterChoice().equals("Xml")) {
            xml.unloadXml(getHashTask());
        } else if (getParameterChoice().equals("Database")) {
            database.unloadTasks(getHashTask());
        } else {
            System.out.println("You have only two options: ");
            System.out.println("Xml and Database!");
        }

        int choice = 0;
        do {
            out.printf(" Hello,%n" +
                    " press 1 to add task%n" +
                    " press 2 to edit task%n" +
                    " press 3 to remove task%n" +
                    " press 4 to see calendar%n" +
                    " press 5 to see list of all tasks%n" +
                    " press 6 for exit%n");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        editTask();
                        break;
                    case 3:
                       removeTask();
                        break;
                    case 4:
                        if (getHashTask().isEmpty()) {
                            System.out.println("You don't have tasks!");
                        } else {
                           calendarMenuOptions();
                        }
                        break;
                    case 5:
                       printTasks();
                        break;
                    case 6:
                        exit();
                        break;
                    default:
                        out.println("Wrong input!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input!");
            }
        } while (choice != 6);
    }
}
