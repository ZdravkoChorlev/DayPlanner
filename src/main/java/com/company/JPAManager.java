package com.company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


public class JPAManager {


    public void saveData(Map<String, Task> hashTask) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("JPADemo");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

         Task t = new Task();
         t.setTaskMap(hashTask);
         manager.persist(t);


         manager.getTransaction().commit();
         manager.close();
    }

    public void unloadData(Map<String, Task> hashTask) {
       EntityManagerFactory factory =
               Persistence.createEntityManagerFactory("JPADemo");
       EntityManager manager = factory.createEntityManager();
       manager.getTransaction().begin();

        Query query = manager.createNativeQuery("SELECT * FROM Task WHERE hour > 0");
        List<Task> list = query.getResultList();
        for (Task task : list) {
            hashTask.put(task.getDay() + "." + task.getHour(), task);
        }
        manager.getTransaction().commit();
        manager.close();
        System.out.println("Database contents delivered...");
    }
}
