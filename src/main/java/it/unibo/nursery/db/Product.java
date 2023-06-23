package it.unibo.nursery.db;

public class Product {
    private int id;
    private String type;
    private float price;
    private String description;
    
    public Product(int id, String type, float price, String description) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }
    
    public float getPrice() {
        return price;
    }
    
    public String getDescription() {
        return description;
    }
}
