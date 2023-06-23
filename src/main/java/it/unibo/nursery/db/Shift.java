package it.unibo.nursery.db;

import java.util.Date;

public class Shift {
    private int departmentId;
    private Date date;
    private int startingTime;
    private int endTime;

    public Shift(int departmentId, Date date, int startingTime, int endTime) {
        this.departmentId = departmentId;
        this.date = date;
        this.startingTime = startingTime;
        this.endTime = endTime;
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

}
