package com.company;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;


@Entity
//@SQLUpdate(sql= "Insert ignore into Task (day, description, hour) value (?, ?, ?)")
public class Task implements Serializable {

    @Id
    @GenericGenerator(name="increment" , strategy="increment")
    @GeneratedValue(generator="increment", strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description", unique = true)
    private String description;
    @Column(name = "day", unique = true)
    private int day;
    @Column(name = "hour", unique = true)
    private int hour;

    @ElementCollection
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Map<String, Task> taskMap;

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<String, Task> hashTask) {
        this.taskMap = hashTask;
    }

    public Task() {

    }

    public Task(String description, int day, int hour) {
        this.description = description;
        this.day = day;
        this.hour = hour;
    }

    public Task(Task t) {
        this(t.getDescription(), t.getDay(), t.getHour());
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getDay() {
        return day;
    }
    public void setDay(Integer day) {
        this.day = day;
    }
    public Integer getHour() {
        return hour;
    }
    public void setHour(Integer hour) {
        this.hour = hour;
    }


    @Override
    public String toString() {
        String date = String.format("%s%s%s%s", this.getDay().toString(), "D-", this.getHour().toString(), "H");
        String result = String.format("%.10s%s%s%n", date, " | ", this.getDescription());
        return (result);
    }
}
