package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.*;

public class FlowerManagerTest {
    @Test
    public void testSingleton() {
        FlowerManager i1 = FlowerManager.getInstance();
        FlowerManager i2 = FlowerManager.getInstance();
        assertSame(i1, i2);
    }
    @Test(expected = IllegalStateException.class)
    public void testSetInstance() {
        FlowerManager i1 = FlowerManager.getInstance();
        FlowerManager.setInstance(i1);
    }
    @Test
    public void testCreateFlower() {
        FlowerManager fm = FlowerManager.getInstance();
        Flower flower = fm.createFlower("orchid");
        assertEquals("orchid", flower.getType().getName());
        Flower f2 = fm.createFlower("orchid");
        assertNotEquals(flower, f2);
    }
    @Test
    public void testGetFlowerType() {
        FlowerManager fm = FlowerManager.getInstance();
        FlowerType t1 = fm.getFlowerType("orchid");
        FlowerType t2 = fm.getFlowerType("orchid");
        FlowerType t3 = fm.getFlowerType("dahlia");
        assertSame(t1, t2);
        assertNotSame(t2,t3);
    }


}
