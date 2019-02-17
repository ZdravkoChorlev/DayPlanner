package com.company;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    CalendarMenu calendarMenu = new CalendarMenu();
    TaskManager taskManager = new TaskManager();

    public Menu() {

    }

    public void menuOptions(Map<String, Task> hashTask) {

        if (getParameterChoice().equals("XML")) {
            xml.unloadXml(hashTask);
        } else if (getParameterChoice().equals("Database")) {
            database.unloadTasks(hashTask);
        } else if (getParameterChoice().equals("JPA")) {
            jpa.unloadData(hashTask);
        } else {
            System.out.println("You have only three options: ");
            System.out.println("XML, Database and JPA!");
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
                        taskManager.addTask();
                        break;
                    case 2:
                        taskManager.editTask();
                        break;
                    case 3:
                        taskManager.removeTask();
                        break;
                    case 4:
                        if (hashTask.isEmpty()) {
                            System.out.println("You don't have tasks!");
                        } else {
                            calendarMenu.calendarMenuOptions();
                        }
                        break;
                    case 5:
                        printTasks(taskManager.getHashTask());
                        break;
                    case 6:
                        exit(taskManager.getHashTask(), taskManager.getDatabaseKeys());
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

    private String parameterChoice = null;
    JPAManager jpa = new JPAManager();
    XMLManager xml = new XMLManager();
    DatabaseManager database = new DatabaseManager();

    public void setParameterchoice(String parameterChoice) {
        this.parameterChoice = parameterChoice;
    }

    public String getParameterChoice() {
        return this.parameterChoice;
    }


    public void exit(Map<String, Task> hashTask, List<String> databaseKeys) {

        if (getParameterChoice().equals("XML")) {
            xml.saveXml(hashTask);
        } else if (getParameterChoice().equals("Database")) {
            database.saveTasks(hashTask, databaseKeys);
        } else if (getParameterChoice().equals("JPA")) {
            jpa.saveData(taskManager.getHashTask());
        } else {
            System.out.println("You have only three options: ");
            System.out.println("XML, Database and JPA!");
        }
        return;
    }

    public void printTasks(Map<String, Task> hashTask) {
        if (!hashTask.isEmpty()) {

            for (Task t : hashTask.values()) {
                System.out.println(t);
            }
            System.out.printf("%n");
        } else {
            System.out.println("No events");
        }
    }
}
