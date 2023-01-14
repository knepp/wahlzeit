package org.wahlzeit.model;

import org.wahlzeit.services.FlowerLog;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static java.util.Objects.hash;

public class FlowerManager {

    private HashMap<Long, Flower> flowers = new HashMap<>();
    private HashMap<Integer, FlowerType> types = new HashMap<>();
    public static long flowerId = 0;


    private static FlowerManager instance = null;

    public static synchronized FlowerManager getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic FlowerManager");
            setInstance(new FlowerManager());
        }
        return instance;
    }

    protected static synchronized void setInstance(FlowerManager flowerManager) throws IllegalStateException {
        if (instance != null) {
            FlowerLog.logError("It was attempted to initialize FlowerManager twice.");
            throw new IllegalStateException("attempt to initialize FlowerManager twice");
        }
        instance = flowerManager;
    }


    protected Flower createFlower(String typeName) {
        FlowerType ft = getFlowerType(typeName);
        Flower result = ft.createInstance();
        flowers.put(result.getId(), result);
        return result;
    }
    protected FlowerType getFlowerType(String name) {
        FlowerType ret = types.get(hash(name));
        if (ret == null) {
            ret = new FlowerType(name);
            types.put(hash(name), ret);
        }
        return ret;
    }
}
