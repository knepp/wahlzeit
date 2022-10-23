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
}
