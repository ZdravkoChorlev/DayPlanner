package com.company;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Tasks_Details")
public class JPAMap {


    @Id
    private int id;

    public JPAMap() {

    }

    @ElementCollection
    @JoinTable(name = "Tasks", joinColumns = @JoinColumn(name = "TASK_ID"))
    private Collection<Task> map = new ArrayList<Task>();

    public Collection<Task> getMap() {
        return map;
    }

    public void setMap(Collection<Task> map) {
        this.map = map;
    }
}
