package com.smartplanner;

public class Plan {
    String name;
    int id;

    public Plan(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Plan(int id){
        this.id = id;
        this.name = new String("Plan " + Integer.toString(id));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
