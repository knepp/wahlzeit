package org.wahlzeit.model;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.fail;
import static org.wahlzeit.model.CartesianCoordinate.getCartesianCoordinate;


/**
 * Test for the Coordinate class.
 */
public class AbstractCoordinateTest {
    @Test
    public void testPreCondGetCartesianDistance() {
        try {
            CartesianCoordinate c1 = getCartesianCoordinate(1,2,3);
            c1.getCartesianDistance(null);
        } catch (IllegalArgumentException exception) {
            return;
        } catch (ValueOutOfRangeException e) {
            //testing post-condition a bit as this exception is thrown when post-condition is not met
            fail();
        }
        fail(); //no exception was thrown
    }

    @Test
    public void testPreCondGetCentralAngle() {
        try {
            CartesianCoordinate c1 = getCartesianCoordinate(1,2,3);
            c1.getCentralAngle(null);
        } catch (IllegalArgumentException exception) {
            return;
        } catch (ValueOutOfRangeException e) {
            //testing post-condition a bit as this exception is thrown when post-condition is not met
            fail();
        }
        fail(); //no exception was thrown
    }
    @Test
    public void testPreCondWriteOn() {
        try {
            CartesianCoordinate c1 = getCartesianCoordinate(1,2,3);
            c1.writeOn(null);
        } catch (IllegalArgumentException exception) {
            return;
        } catch (SQLException | ValueOutOfRangeException e) {
            fail();
        }
        fail(); //no exception was thrown
    }

    @Test
    public void testAssertCoordinateInputNotNull_notNull() {
        CartesianCoordinate c1 = getCartesianCoordinate(1,2,3);
        AbstractCoordinate.assertCoordinateInputNotNull(c1);
    }
    @Test
    public void testAssertCoordinateInputNotNull_null() {
        try {
            AbstractCoordinate.assertCoordinateInputNotNull(null);
        } catch (IllegalArgumentException exception) {
            return;
        }
        fail(); //no exception was thrown
    }
}
