package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {
    @Test
    public void testCreate() {
        CartesianCoordinate coordinate1 = new CartesianCoordinate(1.3,3.2,5.1);
        assertNotNull(coordinate1);
        assertEquals(1.3, coordinate1.getX(), 0.0);
        assertEquals(3.2, coordinate1.getY(), 0.0);
        assertEquals(5.1, coordinate1.getZ(), 0.0);

        CartesianCoordinate coordinate2 = new CartesianCoordinate(-3.5,-3.2,-5.1);
        assertNotNull(coordinate2);
        assertEquals(-3.5, coordinate2.getX(), 0.0);
        assertEquals(-3.2, coordinate2.getY(), 0.0);
        assertEquals(-5.1, coordinate2.getZ(), 0.0);
    }
    @Test
    public void testGetDistance() {
        CartesianCoordinate c1 = new CartesianCoordinate(4,5.7,3.7),
                c2 = new CartesianCoordinate(9.3,45.67,3.5);
        assertEquals(c1.getCartesianDistance(c2), 40.320353,0.001);
        CartesianCoordinate c3 = new CartesianCoordinate(0,5.7,-3.7),
                c4 = new CartesianCoordinate(9.3,-45.67,3.5);
        assertEquals(c3.getCartesianDistance(c4), 52.6992115,0.001);
    }
    @Test
    public void testIsEqual() {
        CartesianCoordinate c1 = new CartesianCoordinate(4,5.7,3.7),
                c2 = new CartesianCoordinate(9.3,45.67,3.5),
                c3 = new CartesianCoordinate(9.3,45.67,3.5);
        assertFalse(c1.isEqual(null));
        assertFalse(c1.isEqual(c2));
        assertTrue(c2.isEqual(c3));
    }
    @Test
    public void testEquals() {
        CartesianCoordinate c1 = new CartesianCoordinate(4,5.7,3.7),
                c2 = new CartesianCoordinate(9.3,45.67,3.5),
                c3 = new CartesianCoordinate(9.3,45.67,3.5);
        assertTrue(c2.equals(c3));
        assertFalse(c1.equals(c2));
        assertFalse(c1.equals(null));
        assertTrue(c1.equals(c1));
    }
    @Test
    public void testHashCode() {
        CartesianCoordinate c1 = new CartesianCoordinate(4,5.7,3.7),
                c2 = new CartesianCoordinate(9.3,45.67,3.5),
                c3 = new CartesianCoordinate(9.3,45.67,3.5);
        assertNotEquals(c1.hashCode(), c2.hashCode());
        assertEquals(c2.hashCode(), c3.hashCode());
    }


    @Test
    public void testAsSpherical() {
        CartesianCoordinate coor = new CartesianCoordinate(4.6, 7.2, 45.23);
        SphericCoordinate coor2 = coor.asSphericCoordinate();
        CartesianCoordinate coor3 = coor2.asCartesianCoordinate();
        assertEquals(coor3.getX(), coor.getX(), 0.1);
        assertEquals(coor3.getY(), coor.getY(), 0.1);
        assertEquals(coor3.getZ(), coor.getZ(), 0.1);
    }
    @Test
    public void testAsCartesianCoordinate() {
        CartesianCoordinate c = new CartesianCoordinate(1,2,3);
        assertEquals(c, c.asCartesianCoordinate());
    }
    @Test
    public void testIsEqualDifferentCoordinates() {
        CartesianCoordinate c1 = new CartesianCoordinate(1,2,3);
        SphericCoordinate c2 = c1.asSphericCoordinate();
        assertTrue(c1.isEqual(c2));
    }
}
