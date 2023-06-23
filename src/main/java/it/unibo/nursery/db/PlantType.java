package it.unibo.nursery.db;

public class PlantType {

    private String scientificName;
    private int floor;
    private int department;

    public PlantType(String scientificName, int floor, int department) {
        this.scientificName = scientificName;
        this.floor = floor;
        this.department = department;
    }

    public String getScientificName() {
        return scientificName;
    }

    public int getFloor() {
        return floor;
    }

    public int getDepartment() {
        return department;
    }

}
