package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.wahlzeit.model.CartesianCoordinate.getCartesianCoordinate;

public class CartesianCoordinateTest {
    @Test
    public void testCreate() {
        CartesianCoordinate coordinate1 = getCartesianCoordinate(1.3,3.2,5.1);
        assertNotNull(coordinate1);
        assertEquals(1.3, coordinate1.getX(), 0.0);
        assertEquals(3.2, coordinate1.getY(), 0.0);
        assertEquals(5.1, coordinate1.getZ(), 0.0);

        CartesianCoordinate coordinate2 = getCartesianCoordinate(-3.5,-3.2,-5.1);
        assertNotNull(coordinate2);
        assertEquals(-3.5, coordinate2.getX(), 0.0);
        assertEquals(-3.2, coordinate2.getY(), 0.0);
        assertEquals(-5.1, coordinate2.getZ(), 0.0);
    }
    @Test
    public void testGetDistance() throws ValueOutOfRangeException {
        CartesianCoordinate c1 = getCartesianCoordinate(4,5.7,3.7),
                c2 = getCartesianCoordinate(9.3,45.67,3.5);
        assertEquals(c1.getCartesianDistance(c2), 40.320353,0.001);
        CartesianCoordinate c3 = getCartesianCoordinate(0,5.7,-3.7),
                c4 = getCartesianCoordinate(9.3,-45.67,3.5);
        assertEquals(c3.getCartesianDistance(c4), 52.6992115,0.001);
    }
    @Test
    public void testIsEqual() throws ValueOutOfRangeException {
        CartesianCoordinate c1 = getCartesianCoordinate(4,5.7,3.7),
                c2 = getCartesianCoordinate(9.3,45.67,3.5),
                c3 = getCartesianCoordinate(9.3,45.67,3.5);
        assertFalse(c1.isEqual(null));
        assertFalse(c1.isEqual(c2));
        assertTrue(c2.isEqual(c3));
    }
    @Test
    public void testEquals() {
        CartesianCoordinate c1 = getCartesianCoordinate(4,5.7,3.7),
                c2 = getCartesianCoordinate(9.3,45.67,3.5),
                c3 = getCartesianCoordinate(9.3,45.67,3.5);
        assertTrue(c2.equals(c3));
        assertFalse(c1.equals(c2));
        assertFalse(c1.equals(null));
        assertTrue(c1.equals(c1));
    }
    @Test
    public void testHashCode() {
        CartesianCoordinate c1 = getCartesianCoordinate(4,5.7,3.7),
                c2 = getCartesianCoordinate(9.3,45.67,3.5),
                c3 = getCartesianCoordinate(9.3,45.67,3.5);
        assertNotEquals(c1.hashCode(), c2.hashCode());
        assertEquals(c2.hashCode(), c3.hashCode());
    }


    @Test
    public void testAsSpherical() throws ValueOutOfRangeException {
        CartesianCoordinate coor = getCartesianCoordinate(4.6, 7.2, 45.23);
        SphericCoordinate coor2 = coor.asSphericCoordinate();
        CartesianCoordinate coor3 = coor2.asCartesianCoordinate();
        assertTrue(coor.isEqual(coor3));

        coor = getCartesianCoordinate(-2.3, 3.2, 1.4);
        coor2 = coor.asSphericCoordinate();
        coor3 = coor2.asCartesianCoordinate();
        assertTrue(coor.isEqual(coor3));
    }
    @Test
    public void testAsCartesianCoordinate() {
        CartesianCoordinate c = getCartesianCoordinate(1,2,3);
        assertEquals(c, c.asCartesianCoordinate());
    }
    @Test
    public void testIsEqualDifferentCoordinates() throws ValueOutOfRangeException {
        CartesianCoordinate c1 = getCartesianCoordinate(1,2,3);
        SphericCoordinate c2 = c1.asSphericCoordinate();
        assertTrue(c1.isEqual(c2));
    }

    //test pre-conditions
    @Test
    public void testPreCondReadFrom() throws  SQLException{
        try {
            CartesianCoordinate.readFrom(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }
}
