package org.wahlzeit.model;

import static java.util.Objects.hash;
import static org.wahlzeit.model.FlowerManager.flowerId;

public class Flower {

    private FlowerType type = null;
    private Location location;
    private String color;
    private long id;

    public Flower(FlowerType type) {
        this.type = type;
        id = setNextId();
    }

    public FlowerType getType() {
        return type;
    }
    public void setType(FlowerType type) {
        this.type = type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    public static synchronized long setNextId() {
        flowerId++;
        return flowerId;
    }

    public long getId() {
        return id;
    }
}
