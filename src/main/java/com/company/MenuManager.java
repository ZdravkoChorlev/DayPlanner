package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;


public class MenuManager extends CalendarMenu {
    Scanner scanner = new Scanner(System.in);

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


    public void exit() {

        if (getParameterChoice().equals("Xml")) {
            xml.saveXml(getHashTask());
        } else if (getParameterChoice().equals("Database")) {
            database.saveTasks(getHashTask(), getDatabaseKeys());
        } else if (getParameterChoice().equals("JPA")) {
                jpa.saveData(getHashTask());
        }

        else {
                System.out.println("You have only three options: ");
                System.out.println("Xml, Database and JPA!");
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
