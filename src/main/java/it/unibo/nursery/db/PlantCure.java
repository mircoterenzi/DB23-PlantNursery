package it.unibo.nursery.db;

public class PlantCure {
    private final int id_prodotto;
    private final String scientificName;
    private final int days_in_care;
    private final int expected_water;
    private final int given_water;
    private final int expected_fertilizer;
    private final int given_fertilizer;

    public PlantCure(int id_prodotto, String scientificName, int days_in_care, int expected_water,
                      int given_water, int expected_fertilizer, int given_fertilizer) {
        this.id_prodotto = id_prodotto;
        this.scientificName = scientificName;
        this.days_in_care = days_in_care;
        this.expected_water = expected_water;
        this.given_water = given_water;
        this.expected_fertilizer = expected_fertilizer;
        this.given_fertilizer = given_fertilizer;
    }

    public int getId_prodotto() {
        return id_prodotto;
    }

    public String getScientificName() {
        return scientificName;
    }

    public int getDays_in_care() {
        return days_in_care;
    }

    public int getExpected_water() {
        return expected_water;
    }

    public int getGiven_water() {
        return given_water;
    }

    public int getExpected_fertilizer() {
        return expected_fertilizer;
    }

    public int getGiven_fertilizer() {
        return given_fertilizer;
    }
}

