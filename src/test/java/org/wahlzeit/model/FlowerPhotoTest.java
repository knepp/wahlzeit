package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.wahlzeit.model.CartesianCoordinate.getCartesianCoordinate;

public class FlowerPhotoTest {
    @Test
    public void constructorsTest() {
        FlowerManager fm = FlowerManager.getInstance();
        Flower f1 = fm.createFlower("Primrose");
        Flower f2 = fm.createFlower("Katniss");
        Flower f3 = fm.createFlower("rose");

        Location location = new Location(getCartesianCoordinate(1,2,3));
        FlowerPhoto ph1 = new FlowerPhoto();
        FlowerPhoto ph2 = new FlowerPhoto(f1);
        FlowerPhoto ph3 = new FlowerPhoto(location);
        FlowerPhoto ph4 = new FlowerPhoto(location, f2);
        FlowerPhoto ph5 = new FlowerPhoto(new PhotoId(987));
        FlowerPhoto ph6 = new FlowerPhoto(new PhotoId(876), location);
        FlowerPhoto ph7 = new FlowerPhoto(new PhotoId(765), location, f3);

        assertEquals("Primrose", ph2.getFlowerName());
        assertEquals("Katniss", ph4.getFlowerName());
        assertEquals("rose", ph7.getFlowerName());
    }
    @Test
    public void getFlowerNameTest() {
        FlowerManager fm = FlowerManager.getInstance();
        Flower f1 = fm.createFlower("Primrose");

        FlowerPhoto ph = new FlowerPhoto(f1);
        assertEquals("Primrose", ph.getFlowerName());
    }
    @Test
    public void setFlowerNameTest() {
        FlowerManager fm = FlowerManager.getInstance();
        Flower f1 = fm.createFlower("Katniss");

        FlowerPhoto ph = new FlowerPhoto();
        ph.setFlowerName(f1);
        assertEquals("Katniss", ph.getFlowerName());
    }
}
