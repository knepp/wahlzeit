package org.wahlzeit.model;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;
import static org.wahlzeit.model.CartesianCoordinate.getCartesianCoordinate;

/**
 * Test for the Coordinate class.
 */
public class LocationTest {
    @Test
    public void testCreateNotNull() {
        try {
            CartesianCoordinate coordinate = getCartesianCoordinate(1.3, 3.2, 5.1);

            Location location = new Location(coordinate);
            assertNotNull(location);
            assertEquals(1.3, location.coordinate.asCartesianCoordinate().getX(), 0.0);
            assertEquals(3.2, location.coordinate.asCartesianCoordinate().getY(), 0.0);
            assertEquals(5.1, location.coordinate.asCartesianCoordinate().getZ(), 0.0);
        }
        catch (ValueOutOfRangeException exception) {
            fail("There was a ValueOutOfRangeException");
        }
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