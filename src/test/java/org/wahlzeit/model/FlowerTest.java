package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.wahlzeit.model.CartesianCoordinate.getCartesianCoordinate;

public class FlowerTest {
    @Test
    public void testGetterSetter() {
        FlowerManager fm = FlowerManager.getInstance();
        Flower flower = fm.createFlower("orchid");
        assertEquals("orchid", flower.getType().getName());

        flower.setType(fm.getFlowerType("dahlia"));
        assertEquals("dahlia", flower.getType().getName());

        Location location = new Location(getCartesianCoordinate(1,2,3));
        flower.setLocation(location);
        assertSame(location, flower.getLocation());

        String color = "blue";
        flower.setColor(color);
        assertEquals(color, flower.getColor());

        Flower flower2 = fm.createFlower("dahlia");
        assertNotEquals(flower.getId(), flower2.getId());
    }

    @Test
    public void testGetSetId() {
        FlowerManager fm = FlowerManager.getInstance();
        Flower f1 = fm.createFlower("orchid");
        Flower f2 = fm.createFlower("orchid");
        assertNotEquals(f1.getId(), f2.getId());
    }
}
