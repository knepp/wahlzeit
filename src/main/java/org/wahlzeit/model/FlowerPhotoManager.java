package org.wahlzeit.model;

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
        return FlowerPhotoFactory.getInstance().createPhoto(rset);
    }
}
