package it.unibo.nursery.db;

public class PlantSold {

    private String name;
    private int quantity;

    public PlantSold(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

}
