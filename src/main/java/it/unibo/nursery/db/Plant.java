package it.unibo.nursery.db;

import java.util.Optional;

public class Plant {
    private final Optional<Integer> id;
    private final String description;
    private final String scientificName;
    private final float length;
    private final float height;
    private final float price;
    private final Optional<Integer> invoice;
    private final Optional<Integer> receipt;

    public Plant(int id, String description, String scientificName, float length, float height, float price,int invoice, int receipt) {
        this.id=Optional.of(id);
        this.description = trimToMaxLength(description, 20);
        this.scientificName = trimToMaxLength(scientificName, 20);
        this.length = length;
        this.height = height;
        this.price = price;
        this.invoice=Optional.of(invoice);
        this.receipt=Optional.ofNullable(receipt);
    }
    public Plant(String description, String scientificName, float length, float height, float price) {
        this.id=Optional.empty();
        this.description = trimToMaxLength(description, 20);
        this.scientificName = trimToMaxLength(scientificName, 20);
        this.length = length;
        this.height = height;
        this.price = price;
        this.invoice=Optional.empty();;
        this.receipt=Optional.empty();;
    }

    public int getId(){
        return id.orElse(null);
    }

    public int getIdInvoice(){
        return invoice.orElse(null);
    }

    public int getIdReceipt(){
        return receipt.orElse(null);
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
