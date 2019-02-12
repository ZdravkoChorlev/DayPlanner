package com.company;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Tasks_Details")
public class Task implements Serializable {

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Id
    private int key = 0;

    @Column(name = "description")
    private String description = null;
    @Column(name = "day")
    private int day = 0;
    @Column(name = "hour")
    private int hour = 0;

    @ElementCollection
    @MapKeyColumn(name = "key_key")
    @JoinTable(name = "example_attributes", joinColumns =
    @JoinColumn(name="example_id"))
    private Map<String, Task> hashTask = new HashMap<String, Task>();

    public Map<String, Task> getHashTask() {
        return hashTask;
    }

    public void setHashTask(Map<String, Task> hashTask) {
        this.hashTask = hashTask;
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
