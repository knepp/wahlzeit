package org.wahlzeit.model;

import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.services.FlowerLog;
import org.wahlzeit.services.Log;
import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

@PatternInstance(
        patternName = "Abstract Factory Pattern",
        participants = {"concrete factory"}
)
@PatternInstance(
        patternName = "Singleton Pattern",
        participants = {"Singleton"}
)
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

    protected static synchronized void setInstance(FlowerPhotoFactory fPhotoFactory) throws IllegalStateException {
        if (instance != null) {
            FlowerLog.logError("It was attempted to initialize FlowerPhotoFactory twice.");
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
