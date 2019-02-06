package com.company;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import javax.persistence.Query;
import java.util.Map;


public class JPAManager {


    public void saveData(Map<String, Task> hashTask) {

         Configuration configuration = new Configuration().configure();
         SessionFactory sessionFactory = configuration.buildSessionFactory();
         Session session = sessionFactory.openSession();
         session.beginTransaction();

         JPAMap map = new JPAMap();
         map.setMap(hashTask.values());
         session.save(map);

         session.getTransaction().commit();
         session.close();
    }

    public void unloadData(Map<String, Task> hashTask) {
       Configuration configuration = new Configuration().configure();
       SessionFactory sessionFactory = configuration.buildSessionFactory();
       Session session = sessionFactory.openSession();
       session.beginTransaction();

        Query queryResult = session.createSQLQuery("SELECT * FROM Tasks;");

       JPAMap map = new JPAMap();
       map.setMap(queryResult.getResultList());
       System.out.println(map.getMap().size());

        System.out.println("Database contents delivered...");
        session.close();
    }
}
