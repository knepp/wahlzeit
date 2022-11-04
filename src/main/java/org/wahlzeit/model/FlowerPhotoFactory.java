package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowerPhotoFactory extends PhotoFactory{

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static FlowerPhotoFactory instance = null;

    public static synchronized FlowerPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic FlowerPhotoFactory");
            setInstance(new FlowerPhotoFactory());
        }
        return instance;
    }

    protected static synchronized void setInstance(FlowerPhotoFactory fPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize (Flower)PhotoFactory twice");
        }
        instance = fPhotoFactory;
    }


    public FlowerPhoto createPhoto() {
        return new FlowerPhoto();
    }

    public FlowerPhoto createPhoto(PhotoId id) {
        return new FlowerPhoto(id);
    }

    public FlowerPhoto createPhoto(ResultSet rs) throws SQLException {
        return new FlowerPhoto(rs);
    }

}
