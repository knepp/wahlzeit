package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Objects;

import static org.wahlzeit.model.CartesianCoordinate.getCartesianCoordinate;

public class SphericCoordinate extends AbstractCoordinate{
    private final double phi, theta, radius;
    private static HashMap<Integer, SphericCoordinate> sphericHashMap = new HashMap<>();

    private SphericCoordinate(double phi, double theta, double radius) throws ValueOutOfRangeException {
        //pre-condition
        if(radius < 0)
            throw new ValueOutOfRangeException("Radius must be at least 0. Exception thrown in SphericCoordinate constructor.");
        //method
        this.phi = moduloAngle(phi);
        this.theta = moduloAngle(theta);
        this.radius = radius;
        //post-condition
        assertSphericValuesInRange(this);
    }

    public static SphericCoordinate getSphericCoordinate(double phi, double theta, double radius) throws ValueOutOfRangeException {
        phi = moduloAngle(phi);
        theta = moduloAngle(theta);
        int hash = sphericHash(phi, theta, radius);
        SphericCoordinate c = sphericHashMap.get(hash);
        //check if coordinate in hashMap
        if (c != null)
            return c;
        //coordinate does not exist yet -> create and put into hashMap
        c = new SphericCoordinate(phi,theta,radius);
        sphericHashMap.put(hash, c);
        return c;
    }

    public double getPhi() {
        return phi;
    }
    public double getTheta() {
        return theta;
    }
    public double getRadius() {
        return radius;
    }

    private static int sphericHash(double phi, double theta, double radius) {
        //pre-condition
        if(radius < 0)
            throw new IllegalArgumentException("Radius must be at least 0. Exception thrown in SphericCoordinate constructor.");
        //assert class invariant
        assertClassInvariants();
        //no pre-condition as this cannot be null and every coordinate has x, y and z, there is no other possibility
        return Objects.hash(Math.round(phi/EPSILON),
                Math.round(theta/EPSILON),
                Math.round(radius/EPSILON));
        //no post-condition needed
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws ValueOutOfRangeException {
        //assert class invariant
        assertClassInvariants();
        //pre-condition
        assertSphericValuesInRange(this);
        //method
        return getCartesianCoordinate(radius * Math.sin(theta)*Math.cos(phi),
                radius * Math.sin(theta)*Math.sin(phi),
                radius * Math.cos(theta));
        //no post-condition needed
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        //assert class invariant
        assertClassInvariants();
        //no pre-condition needed
        return this;
        //no post-condition needed
    }

    /* disclaimer: method copied from http://www.java2s.com/example/java-utility-method/double-number-mod/moduloangle-double-angle-0a3b6.html
     * License: Open Source License
     */
    public static double moduloAngle(double angle) throws ValueOutOfRangeException {
        //no pre-condition as double cannot be null
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        //post-condition
        if(angle < -Math.PI || angle > Math.PI)
            throw new ValueOutOfRangeException("Angle computed in moduloAngle is out of range - post-condition failed.");
        return angle;
    }

    protected static void assertSphericValuesInRange(SphericCoordinate coor) throws ValueOutOfRangeException {
        double p = coor.getPhi(), t = coor.getTheta(), r = coor.getRadius();
        if(p < -Math.PI || p > Math.PI)
            throw new ValueOutOfRangeException("Phi is out of range in assertSphericValuesInRange.");
        if(t < -Math.PI || t > Math.PI)
            throw new ValueOutOfRangeException("Theta is out of range in assertSphericValuesInRange.");
        if (r < 0)
            throw new ValueOutOfRangeException("Radius cannot be less than 0; exception in assertSphericValuesInRange.");
    }
}
