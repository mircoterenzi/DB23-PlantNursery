package it.unibo.nursery.db;

import java.util.Date;

public class Employee {
    private String name;
    private String surname;
    private String taxCode;
    private float salary;
    private Date employmentDate;
    private int id;

    public Employee(String name, String surname, String taxCode, float salary, Date employmentDate, int id) {
        this.name = name;
        this.surname = surname;
        this.taxCode = taxCode;
        this.salary = salary;
        this.employmentDate = employmentDate;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public float getSalary() {
        return salary;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public int getId() {
        return id;
    }
}
