package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the Coordinate class.
 */
public class CoordinateTest {
    @Test
    public void testCreate() {
        Coordinate coordinate1 = new Coordinate(1.3,3.2,5.1);
        assertNotNull(coordinate1);
        assertEquals(1.3, coordinate1.getX(), 0.0);
        assertEquals(3.2, coordinate1.getY(), 0.0);
        assertEquals(5.1, coordinate1.getZ(), 0.0);

        Coordinate coordinate2 = new Coordinate(-3.5,-3.2,-5.1);
        assertNotNull(coordinate2);
        assertEquals(-3.5, coordinate2.getX(), 0.0);
        assertEquals(-3.2, coordinate2.getY(), 0.0);
        assertEquals(-5.1, coordinate2.getZ(), 0.0);
    }
    @Test
    public void testGetDistance() {
        Coordinate c1 = new Coordinate(4,5.7,3.7),
                c2 = new Coordinate(9.3,45.67,3.5);
        assertEquals(c1.getDistance(c2), 40.320353,0.001);
        Coordinate c3 = new Coordinate(0,5.7,-3.7),
                c4 = new Coordinate(9.3,-45.67,3.5);
        assertEquals(c3.getDistance(c4), 52.6992115,0.001);
    }
    @Test
    public void testIsEqual() {
        Coordinate c1 = new Coordinate(4,5.7,3.7),
                c2 = new Coordinate(9.3,45.67,3.5),
                c3 = new Coordinate(9.3,45.67,3.5);
        assertFalse(c1.isEqual(null));
        assertFalse(c1.isEqual(c2));
        assertTrue(c2.isEqual(c3));
    }
    @Test
    public void testEquals() {
        Coordinate c1 = new Coordinate(4,5.7,3.7),
                c2 = new Coordinate(9.3,45.67,3.5),
                c3 = new Coordinate(9.3,45.67,3.5);
        assertTrue(c2.equals(c3));
        assertFalse(c1.equals(c2));
        assertFalse(c1.equals(null));
        assertTrue(c1.equals(c1));
    }
    @Test
    public void testHashCode() {
        Coordinate c1 = new Coordinate(4,5.7,3.7),
                c2 = new Coordinate(9.3,45.67,3.5),
                c3 = new Coordinate(9.3,45.67,3.5);
        assertNotEquals(c1.hashCode(), c2.hashCode());
        assertEquals(c2.hashCode(), c3.hashCode());
    }
}
