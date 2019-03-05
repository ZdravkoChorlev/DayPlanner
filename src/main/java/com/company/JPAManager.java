package com.company;

import org.hibernate.HibernateException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.Map;


public class JPAManager {

    private static final String DB_URL = "jdbc:mysql://localhost/dayPlanner";
    private static final String USERNAME = "zdravkojava";
    private static final String PASSWORD = "newpassword";

    public void saveData(Map<String, Task> hashTask) {

        try {
            EntityManagerFactory factory =
                    Persistence.createEntityManagerFactory("JPADemo");
            EntityManager manager = factory.createEntityManager();

            Task task = new Task();
            task.setTaskMap(hashTask);

                manager.getTransaction().begin();
                manager.persist(task);
                manager.getTransaction().commit();
                manager.close();

        } catch (HibernateException e) {
            System.out.println("Saving exception!");
        }

    }

    public void unloadData(Map<String, Task> hashTask) {


        try {

           Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = null;
            ResultSet resultSet = null;
            Task t;

            statement = connection.createStatement();
            resultSet = statement.
                    executeQuery("SELECT description, day,hour FROM Task_Details WHERE hour > 0");
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                int day = resultSet.getInt("day");
                int hour = resultSet.getInt("hour");
                t = new Task(description, day, hour);
                hashTask.put(day + "." + hour, t);
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Unloading exception!");
        }
    }
}
