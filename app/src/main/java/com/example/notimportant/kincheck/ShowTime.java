package com.example.notimportant.kincheck;


public class ShowTime {
    String id;
    String name;
    String times;

    public ShowTime(String id, String name, String times) {
        this.id = id;
        this.name = name;
        this.times = times;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public ShowTime(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
