package com.company;

import java.util.*;
import static java.lang.System.out;

public class CalendarMenu {

    Scanner scanner = new Scanner(System.in);
    TaskController taskController = new TaskController();

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
                        printTaskForTheMonth(taskController.getHashTask());
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

    public void printTasksForSingleDay() {
        try {
            System.out.printf("Please choose a day:%n");
            Integer day = Integer.parseInt(scanner.nextLine());
            taskController.tasksForDay(day);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }

    public void printTasksForGivenWeek() {
        try {
            out.printf("Please choose a week: [1-4]%n");
            int week = Integer.parseInt(scanner.nextLine());
            if (week < 1 || week > 4) {
                out.println("invalid week!");
                return;
            }

            int begin = week * 7 - 6;
            int end = week * 7;
            if (begin == 22 && end == 28) {
                for (int i = begin; i <= end + 3; i++) {
                    taskController.tasksForDay(i);
                }
            } else {
                for (int i = begin; i <= end; i++) {
                    taskController.tasksForDay(i);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }

    public void printTaskForTheMonth(Map<String, Task> hashTask) {
        if (!hashTask.isEmpty()) {
            String border = new String(new char[60]).replace("\0", "-");
            System.out.printf("%s%n", border);

            List<Task> listOfTasks = new ArrayList<Task>(hashTask.values());

            Collections.sort(listOfTasks, new Comparator<Task>() {
                public int compare(Task task1, Task task2) {
                    return task1.getHour().compareTo(task2.getHour());
                }
            });

            Collections.sort(listOfTasks, new Comparator<Task>() {
                public int compare(Task task1, Task task2) {
                    return task1.getDay().compareTo(task2.getDay());
                }
            });

            for (Task value : listOfTasks) {
                System.out.println(value.toString());
            }
        } else {
            System.out.println("No tasks!");
        }
    }

    public void printTasksForGivenPeriod() {
        try {
            out.printf("Please enter first day: %n");
            int firstDay = Integer.parseInt(scanner.nextLine());
            if (firstDay < 1 || firstDay > 31) {
                out.println("invalid input!");
                return;
            }
            out.printf("Please enter second day: %n");
            int secondDay = Integer.parseInt(scanner.nextLine());

            if (secondDay < 1 || secondDay > 31) {
                out.println("invalid input!");
                return;
            }

            if (firstDay < secondDay) {
                int beginDay = firstDay;
                int endDay = secondDay;
                for (int i = beginDay; i <= endDay; i++) {
                    taskController.tasksForDay(i);
                }
                System.out.println();

            } else {
                out.println("Invalid range!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }
}