package it.unibo.nursery.db;

import java.util.Date;

public class Shift {
    private int departmentId;
    private Date date;
    private int startingTime;
    private int endTime;
    private int id;

    public Shift(int departmentId, Date date, int startingTime, int endTime, int id) {
        this.departmentId = departmentId;
        this.date = date;
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Date getDate() {
        return date;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getId() {
        return id;
    }

}
