package com.example.notimportant.kincheck;

import java.util.ArrayList;

public class Theater {
    private String id;
    private String name;
    private String info;
    private String location;
    private ArrayList<ShowTime> showTimes;

    public Theater(String id, String name, String info, String location) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.location = location;
        this.showTimes = new ArrayList<ShowTime>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addShowTime(ShowTime showTime){
        this.showTimes.add(showTime);
    }

    public ArrayList<ShowTime> getShowTimes(){
        return this.showTimes;
    }
}
