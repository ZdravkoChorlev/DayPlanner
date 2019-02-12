package com.company;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.Map;


public class JPAManager {

    Task t = new Task();
    public void saveData(Map<String, Task> hashTask) {

         Configuration configuration = new Configuration().configure();
         SessionFactory sessionFactory = configuration.buildSessionFactory();
         Session session = sessionFactory.openSession();
         session.beginTransaction();

         Task f = new Task("asfs",3, 4);
         t.setHashTask(hashTask);
         t.getHashTask();
         session.save(t.getHashTask());

         session.getTransaction().commit();
         session.close();
    }

    public void unloadData(Map<String, Task> hashTask) {
       /*Configuration configuration = new Configuration().configure();
       SessionFactory sessionFactory = configuration.buildSessionFactory();
       Session session = sessionFactory.openSession();
       session.beginTransaction();


        System.out.println("Database contents delivered...");
        session.close();*/
    }
}
