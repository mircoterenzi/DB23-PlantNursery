package it.unibo.nursery.db;

public class Plant {
    private final String description;
    private final String scientificName;
    private final float length;
    private final float height;
    private final float price;

    public Plant(String description, String scientificName, float length, float height, float price) {
        this.description = trimToMaxLength(description, 20);
        this.scientificName = trimToMaxLength(scientificName, 20);
        this.length = length;
        this.height = height;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getScientificName() {
        return scientificName;
    }

    public float getLength() {
        return length;
    }

    public float getHeight() {
        return height;
    }

    public float getPrice() {
        return price;
    }

    private String trimToMaxLength(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength);
        }
    }
}
