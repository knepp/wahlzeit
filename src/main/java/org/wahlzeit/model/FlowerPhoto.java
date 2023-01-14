package org.wahlzeit.model;

import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.services.FlowerLog;

import java.sql.ResultSet;
import java.sql.SQLException;

@PatternInstance(
        patternName = "Abstract Factory Pattern",
        participants = {"concrete product"}
)
public class FlowerPhoto extends Photo{
    private String flowerName;  //optional: name of the flower in the picture
    private Flower flower;
    public FlowerPhoto() {
        super();
        this.flower = null;
        this.flowerName = "";
    }
    public FlowerPhoto(Flower flower) {
        super();
        this.flower = flower;
        this.flowerName = flower.getType().getName();
    }
    public FlowerPhoto(Location location) {
        super(location);
        this.flower = null;
        this.flowerName = "";
    }
    public FlowerPhoto(Location location, Flower flower) {
        super(location);
        this.flower = flower;
        this.flowerName = flower.getType().getName();
    }
    public FlowerPhoto(PhotoId myId) {
        super(myId);
        this.flower = null;
        this.flowerName = "";
    }
    public FlowerPhoto(PhotoId myId, Flower flower) {
        super(myId);
        this.flower = flower;
        this.flowerName = flower.getType().getName();
    }
    public FlowerPhoto(PhotoId myId, Location location) {
        super(myId, location);
        this.flower = null;
        this.flowerName = "";
    }
    public FlowerPhoto(PhotoId myId, Location location, Flower flower) {
        super(myId, location);
        this.flower = flower;
        this.flowerName = flower.getType().getName();
    }
    public FlowerPhoto(ResultSet rset) throws SQLException {
        try {
            readFrom(rset);
        } catch (SQLException ex) {
            FlowerLog.logError("FlowerPhoto could not be created due to an SQL error.");
            throw ex;
        }
    }

    //getter and setter for the flower name
    public String getFlowerName() {
        return flower.getType().getName();
    }
    public void setFlowerName(Flower flower) {
        this.flower = flower;
        flowerName = flower.getType().getName();
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        try {
            super.readFrom(rset);
            this.flowerName = rset.getString("flower_name");
        } catch (SQLException ex) {
            FlowerLog.logError("readFrom in FlowerPhoto failed.");
            throw ex;
        }
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        try {
            super.writeOn(rset);
            rset.updateString("flower_name", flowerName);
        } catch (SQLException ex) {
            FlowerLog.logError("writeOn failed in FlowerPhoto");
            throw ex;
        }
    }
}
