package com.company;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();


        List<Task> tasks = manager
                .createNativeQuery("Select * from Task Where hour > 0", Task.class)
                .getResultList();

        for (Task task : tasks) {
            hashTask.put(task.getDay() + "." + task.getHour(), task);
        }
        manager.getTransaction().commit();
        manager.close();
    }
}
