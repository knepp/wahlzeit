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
        Location location = new Location(getCartesianCoordinate(1,2,3));
        FlowerPhoto ph1 = new FlowerPhoto();
        FlowerPhoto ph2 = new FlowerPhoto("Primrose");
        FlowerPhoto ph3 = new FlowerPhoto(location);
        FlowerPhoto ph4 = new FlowerPhoto(location, "Katniss");
        FlowerPhoto ph5 = new FlowerPhoto(new PhotoId(987));
        FlowerPhoto ph6 = new FlowerPhoto(new PhotoId(876), location);
        FlowerPhoto ph7 = new FlowerPhoto(new PhotoId(765), location, "rose");

        assertEquals(ph2.getFlowerName(), "Primrose");
        assertEquals(ph4.getFlowerName(), "Katniss");
        assertEquals(ph7.getFlowerName(), "rose");
    }
    @Test
    public void getFlowerNameTest() {
        FlowerPhoto ph = new FlowerPhoto("Primrose");
        assertEquals("Primrose", ph.getFlowerName());
    }
    @Test
    public void setFlowerNameTest() {
        FlowerPhoto ph = new FlowerPhoto();
        ph.setFlowerName("Katniss");
        assertEquals("Katniss", ph.getFlowerName());
    }
}
