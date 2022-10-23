package org.wahlzeit.model;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

/**
 * Test for the Coordinate class.
 */
public class LocationTest {
    @Test
    public void testCreateNotNull() {
        Coordinate coordinate = new Coordinate(1.3,3.2,5.1);

        Location location = new Location(coordinate);
        assertNotNull(location);
        assertEquals(1.3, location.coordinate.getX(), 0.0);
        assertEquals(3.2, location.coordinate.getY(), 0.0);
        assertEquals(5.1, location.coordinate.getZ(), 0.0);
    }
    @Test
    public void testCreateNull() {
        Location location;
        try {
            location = new Location(null);
        } catch (InvalidParameterException exception) {
            location = null;
        }
        assertNull(location);
    }

}