package com.company;

import java.util.Scanner;

import static java.lang.System.out;

public class MenuManager extends CalendarMenu {
    Scanner scanner = new Scanner(System.in);

    private String parameterChoice = null;

    XMLManager xml = new XMLManager();
    DatabaseManager database = new DatabaseManager();

    public void setParameterchoice(String parameterChoice) {
        this.parameterChoice = parameterChoice;
    }

    public String getParameterChoice() {
        return this.parameterChoice;
    }


    public void exit() {

        if (getParameterChoice().equals("Xml")) {
            xml.saveXml(getHashTask());
        } else if (getParameterChoice().equals("Database")) {
            database.saveTasks(getHashTask(), getDatabaseKeys());
        } else {
            System.out.println("You have only two options: ");
            System.out.println("Xml and Database!");
        }
        return;
    }


    public void printTasks() {
        if (!getHashTask().isEmpty()) {

            for (Task t : getHashTask().values()) {
                System.out.println(t);
            }
            System.out.printf("%n");
        } else {
            System.out.println("No events");
        }
    }
}
