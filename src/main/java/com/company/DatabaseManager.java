package com.company;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost/dayPlanner";
    private static final String USERNAME = "zdravkojava";
    private static final String PASSWORD = "newpassword";

    Connection connection = null;
    Statement statement = null;

    public DatabaseManager() {

    }

    public void saveTasks(Map<String, Task> hashTask, List<String> databaseKeys) {
        try {

                String[] input = Scanner.class.toString().split(" ");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String table = "CREATE TABLE IF NOT EXISTS tasks(" +
                    "hashKey VARCHAR(255) PRIMARY KEY," +
                    "description TEXT," +
                    "day INTEGER," +
                    "hour INTEGER);";
            statement.executeUpdate(table);

            for (Task task : hashTask.values()) {
                try {
                    String key = task.getDay() + "." + task.getHour();
                    String query = "INSERT INTO tasks VALUES('" + key + "', '" + task.getDescription() +
                            "', " + task.getDay() + ", " + task.getHour() + ") " +
                            "ON DUPLICATE KEY UPDATE description='" + task.getDescription() + "' ," +
                            " day=" + task.getDay() + ", hour=" + task.getHour() + ";";
                    statement.executeUpdate(query);
                } catch(SQLException e) {
                    System.out.println("Error in inserting tasks!");
                }
            }

            for (String databaseKey : databaseKeys) {
                try {
                    String delete = "DELETE FROM tasks WHERE hashKey='" + databaseKey + "';";
                    statement.executeUpdate(delete);
                } catch (SQLException e) {
                    System.out.println("Error with manipulating with the tasks!");
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Database loading exception!");
            e.printStackTrace();
        }
    }

    public void unloadTasks(Map<String, Task> hashTask) {

        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement st = null;
            ResultSet rs = null;
            Task t;
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM tasks");
            while (rs.next()) {
                String description = rs.getString("description");
                int day = rs.getInt("day");
                int hour = rs.getInt("hour");
                t = new Task(description, day, hour);
                hashTask.put(day + "." + hour, t);
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Unloading exception!");
        }
    }
}
