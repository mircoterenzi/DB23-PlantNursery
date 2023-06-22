package it.unibo.nursery.db;

public class CarePlan {
    private int id;
    private float water;
    private String light;
    private float fertilizer;
    private float minTemp;
    private float maxTemp;

    public CarePlan(int id, float water, String light, float fertilizer, float minTemp, float maxTemp) {
        this.id = id;
        this.water = water;
        this.light = light;
        this.fertilizer = fertilizer;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public int getId() {
        return id;
    }

    public float getWater() {
        return water;
    }

    public String getLight() {
        return light;
    }

    public float getFertilizer() {
        return fertilizer;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }
}
