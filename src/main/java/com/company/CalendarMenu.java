package com.company;

import java.util.*;
import static java.lang.System.out;

public class CalendarMenu extends CalendarMenuManager {

    Scanner scanner = new Scanner(System.in);

    public void calendarMenuOptions() {

        int choice = 0;
        do {
            out.printf(" Hello,%n" +
                    " press 1 for all tasks for a given day%n" +
                    " press 2 for a week choice%n" +
                    " press 3 for all tasks in a month%n" +
                    " press 4 for tasks between dates%n" +
                    " press 5 for return to menu%n");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        printTasksForSingleDay();
                        break;
                    case 2:
                        printTasksForGivenWeek();
                        break;
                    case 3:
                        printTaskForTheMonth();
                        break;
                    case 4:
                        printTasksForGivenPeriod();
                        break;
                    case 5:
                        break;
                    default:
                        out.println("Wrong input!");
                        break;
                }
            } catch (NumberFormatException e) {
                out.println("Error, wrong input type!");
            }
        } while (choice != 5);
    }
}