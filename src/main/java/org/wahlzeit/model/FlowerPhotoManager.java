package org.wahlzeit.model;

import org.wahlzeit.services.FlowerLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowerPhotoManager extends PhotoManager {
    protected static final FlowerPhotoManager instance = new FlowerPhotoManager();

    public static FlowerPhotoManager getInstance() {
        return instance;
    }
    public FlowerPhotoManager() {
        photoTagCollector = PhotoFactory.getInstance().createPhotoTagCollector();
    }
    protected FlowerPhoto createObject(ResultSet rset) throws SQLException {
        try {
            FlowerPhoto ph = FlowerPhotoFactory.getInstance().createPhoto(rset);
            return ph;
        } catch (SQLException exception) {
            FlowerLog.logError("FlowerPhoto could not be created in FlowerPhotoManager.");
            throw exception;
        }
    }
}
