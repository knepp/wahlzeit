package org.wahlzeit.model;

import org.wahlzeit.services.FlowerLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowerPhoto extends Photo{
    private String flowerName;  //optional: name of the flower in the picture
    public FlowerPhoto() {
        super();
        this.flowerName = "";
    }
    public FlowerPhoto(String flowerName) {
        super();
        this.flowerName = flowerName;
    }
    public FlowerPhoto(Location location) {
        super(location);
        this.flowerName = "";
    }
    public FlowerPhoto(Location location, String flowerName) {
        super(location);
        this.flowerName = flowerName;
    }
    public FlowerPhoto(PhotoId myId) {
        super(myId);
        this.flowerName = "";
    }
    public FlowerPhoto(PhotoId myId, String flowerName) {
        super(myId);
        this.flowerName = flowerName;
    }
    public FlowerPhoto(PhotoId myId, Location location) {
        super(myId, location);
        this.flowerName = "";
    }
    public FlowerPhoto(PhotoId myId, Location location, String flowerName) {
        super(myId, location);
        this.flowerName = flowerName;
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
        return flowerName;
    }
    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
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
