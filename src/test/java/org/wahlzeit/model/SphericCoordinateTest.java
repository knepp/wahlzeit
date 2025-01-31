package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.wahlzeit.model.CartesianCoordinate.getCartesianCoordinate;
import static org.wahlzeit.model.SphericCoordinate.getSphericCoordinate;

public class SphericCoordinateTest {
    @Test
    public void testIsEqual() throws ValueOutOfRangeException {
        SphericCoordinate c1 = getSphericCoordinate(1,2,3),
                c2 = getSphericCoordinate(1,2,3),
                c3 = getSphericCoordinate(3,2,1);
        assertTrue(c1.isEqual(c2));
        assertFalse(c1.isEqual(c3));
    }

    @Test
    public void testAsSpherical() throws ValueOutOfRangeException {
        SphericCoordinate c = getSphericCoordinate(1.4,5.7,3.1);
        assertTrue(c == c.asSphericCoordinate());
    }

    @Test
    public void testAsCartesianCoordinate() throws ValueOutOfRangeException {
        SphericCoordinate c = getSphericCoordinate(1.1,2.7,3.3);
        SphericCoordinate coor2 = c.asCartesianCoordinate().asSphericCoordinate();
        assertEquals(c.getPhi(), coor2.getPhi(), 0.1);
        assertEquals(c.getTheta(), coor2.getTheta(), 0.1);
        assertEquals(c.getRadius(), coor2.getRadius(), 0.1);
    }
    @Test
    public void testIsEqualDifferentCoordinates() throws ValueOutOfRangeException {
        SphericCoordinate c1 = getSphericCoordinate(1.4,2.3,3.2);
        CartesianCoordinate c2 = c1.asCartesianCoordinate();
        assertTrue(c1.isEqual(c2));
    }
    @Test
    public void centralAngleTest() throws ValueOutOfRangeException {
        SphericCoordinate c1 = getSphericCoordinate(3, 2, 1),
                c2 = getSphericCoordinate(2,1,3);
        assertEquals(1.212248, c1.getCentralAngle(c2), 0.0001);
        assertEquals(0, c1.getCentralAngle(c1), 0);
    }
    @Test
    public void testMyModulo() throws ValueOutOfRangeException {
        double a = 1.23;
        assertEquals(a, SphericCoordinate.moduloAngle(a + (2*Math.PI)), 0.0001);
        a = 3.14;   //edge case - close to pi
        assertEquals(a, SphericCoordinate.moduloAngle(a + (2*Math.PI)), 0.0001);
        a = -1.23;
        assertEquals(a, SphericCoordinate.moduloAngle(a + (2*Math.PI)), 0.0001);
        a = -3.14;  //edge case - close to -pi
        assertEquals(a, SphericCoordinate.moduloAngle(a + (2*Math.PI)), 0.0001);
        a = 0;
        assertEquals(a, SphericCoordinate.moduloAngle(a + (2*Math.PI)), 0.0001);
    }


    //testing pre-conditions
    @Test
    public void testConstructorPreCond_noException() throws ValueOutOfRangeException {
        SphericCoordinate c = getSphericCoordinate(1,2,5);
    }@Test
    public void testConstructorPreCond_exception() throws ValueOutOfRangeException {
        try {
            SphericCoordinate c = getSphericCoordinate(1,2,-5);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }
    @Test
    public void testGetSphericCoordinate() throws ValueOutOfRangeException {
        SphericCoordinate c1 = getSphericCoordinate(1,2,3);
        SphericCoordinate c2 = getSphericCoordinate(1,2,3);
        assertSame(c1, c2);
        c2 = getSphericCoordinate(1,2.0000003,3);
        assertSame(c1,c2);
        assertSame(c1, c1.asCartesianCoordinate().asSphericCoordinate());
    }
}
