package com.company;

import java.util.*;

import static java.lang.System.out;

public class CalendarMenuManager extends TaskManager {
Scanner scanner = new Scanner(System.in);

    public void printTasksForSingleDay() {
        try {

            System.out.printf("Please choose a day:%n");
            Integer day = Integer.parseInt(scanner.nextLine());
            tasksForDay(day);
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
                    tasksForDay(i);
                }
            } else {
                for (int i = begin; i <= end; i++) {
                    tasksForDay(i);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }

    public void printTaskForTheMonth() {
        if (!getHashTask().isEmpty()) {
            String border = new String(new char[60]).replace("\0", "-");
            System.out.printf("%s%n", border);

            List<Task> listOfTasks = new ArrayList<Task>(getHashTask().values());

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
                    tasksForDay(i);
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
