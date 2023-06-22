package it.unibo.nursery.db;

public class Accessory {
    private final String description;
    private final String type;

    public Accessory(String description, String type) {
        this.description = trimToMaxLength(description, 20);
        this.type = trimToMaxLength(type, 20);
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    private String trimToMaxLength(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength);
        }
    }
}

