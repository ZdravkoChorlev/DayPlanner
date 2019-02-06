package com.company;
import javax.persistence.*;

@Embeddable
public class Task {

    @Column(name = "description")
    private String description = null;
    @Column(name = "day")
    private int day = 0;
    @Column(name = "hour")
    private int hour = 0;

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
