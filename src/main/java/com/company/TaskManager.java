package com.company;

import java.util.*;
import static java.lang.System.out;

public class TaskManager {

    Scanner scanner = new Scanner(System.in);
    private int counter = 0;
    private boolean flag = true;

    private List<String> databaseKeys = new ArrayList<String>();

    public void setDatabaseKeys(List<String> databaseKeys) { this.databaseKeys = databaseKeys; }
    public List<String> getDatabaseKeys() { return this.databaseKeys; }

    private static Map<String, Task> hashTask = new TreeMap<String, Task>();
    public void setHashTask(Map<String, Task> hashTask) {
        this.hashTask = hashTask;
    }
    public Map<String, Task> getHashTask() {
       return this.hashTask;
    }



    public TaskManager() {

    }

    public void addTask() {

        try {
            out.println("Please add description: ");
            String description = scanner.nextLine();
            out.println("Please add day: ");
            int day = Integer.parseInt(scanner.nextLine());
            out.println("Please add hour: ");
            int hour = Integer.parseInt(scanner.nextLine());

            if (validateDate(day, hour)) {
                Task task = new Task(description, day, hour);
                String taskKey = day + "." + hour;

                if (!getHashTask().containsKey(taskKey)) {
                   // hashTask.put(taskKey, task);
                    getHashTask().put(taskKey, task);
                    System.out.println(getHashTask().size());

                } else {
                    out.println("For this day and hour you have already added a task. ");
                    System.out.println();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }


    public void editTask() {
        try {
            if (!getHashTask().isEmpty()) {
                out.println("Please enter a day: ");
                int day = Integer.parseInt(scanner.nextLine());
                out.println("Please enter an hour");
                int hour = Integer.parseInt(scanner.nextLine());

                if (validateDate(day, hour)) {

                    String taskKey = day + "." + hour;
                    if (getHashTask().containsKey(taskKey)) {

                        String userInput = null;
                        editTaskParameters(taskKey, userInput, day, hour);
                        //The idea for solving the edit task problem...
                         //hashTask.remove(taskKey, task);

                        databaseKeys.add(taskKey);

                        if(counter == 3 || flag == false) {
                            databaseKeys.remove(taskKey);
                        }

                    } else {
                        out.println("No such event");
                    }
                }

            } else {
                out.println("No tasks");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }

    public void removeTask() {

            try {
                if (!getHashTask().isEmpty()) {
                    out.println("Please enter a day: ");
                    int day = Integer.parseInt(scanner.nextLine());
                    out.println("Please enter an hour: ");
                    int hour = Integer.parseInt(scanner.nextLine());

                    String taskKey = day + "." + hour;

                    if (validateDate(day, hour)) {
                        if (getHashTask().containsKey(taskKey)) {
                            getHashTask().remove(taskKey);
                            databaseKeys.add(taskKey);

                            System.out.println("Removed");
                            System.out.println();
                        } else {
                            out.println("No such event");
                        }
                    }

                } else {
                    out.println("No tasks");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input!");
            }
    }

    public void editTaskParameters(String taskKey, String userInput, int day, int hour) {

        do {
            out.println("Do you want to change the description? Yes/No");
            userInput = scanner.nextLine();
        } while (!(("yes").equalsIgnoreCase(userInput))
                && !("no".equalsIgnoreCase(userInput)));

        if ("yes".equalsIgnoreCase(userInput)) {
            System.out.println("Please input new description: ");
            String setNewDescription = scanner.nextLine();
            getHashTask().get(taskKey).setDescription(setNewDescription);
            flag = false;
        } else {
            counter++;
            do {
                out.println("Do you want to change the day? Yes/No");
                userInput = scanner.nextLine();
            } while (!(("yes").equalsIgnoreCase(userInput))
                    && !("no".equalsIgnoreCase(userInput)));

            if ("yes".equalsIgnoreCase(userInput)) {
                System.out.println("Please input new day: ");
                try {
                    int setNewDay = Integer.parseInt(scanner.nextLine());
                    getHashTask().get(taskKey).setDay(setNewDay);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input!");
                }

            } else {
                counter++;
                do {
                    out.println("Do you want to change the hour? Yes/No");
                    userInput = scanner.nextLine();
                } while (!(("yes").equalsIgnoreCase(userInput))
                        && !("no".equalsIgnoreCase(userInput)));

                if ("yes".equalsIgnoreCase(userInput)) {
                    System.out.println("Please input new hour: ");
                    try {
                        int setNewHour = Integer.parseInt(scanner.nextLine());
                         getHashTask().get(taskKey).setHour(setNewHour);

                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input!");
                    }
                } else {
                    counter++;
                    return;
                }
            }
        }
    }

    public void tasksForDay(int day) {
        if (day < 1 || day > 31) {
            out.println("invalid day!");
            return;
        }

        List<Task> tasksForTheDay = new ArrayList<Task>();
        for (Task value : getHashTask().values()) {
            Task t = new Task(value);
            if (day == value.getDay()) {
                tasksForTheDay.add(value);
            }
        }

        Collections.sort(tasksForTheDay, new Comparator<Task>() {
            public int compare(Task u1, Task u2) {
                return u1.getDay().compareTo(u2.getDay());
            }
        });

        Collections.sort(tasksForTheDay, new Comparator<Task>() {
            public int compare(Task u1, Task u2) {
                return u1.getHour().compareTo(u2.getHour());
            }
        });

        if (!tasksForTheDay.isEmpty()) {
            String border = new String(new char[60]).replace("\0", "-");
            System.out.printf("Day: %d%n%s%n", day, border);

            for (Task task : tasksForTheDay) {
                System.out.printf("%s%n", task.toString());
            }
            System.out.println();
        } else {
            System.out.printf("You are free on %s%n", day);
        }
    }

    public boolean validateDate(int day, int hour) {
        if ((day < 1 || day > 31) || (hour < 0 || hour > 23)) {
            out.println("Wrong data input!");
            return false;
        } else {
            return true;
        }
    }
}