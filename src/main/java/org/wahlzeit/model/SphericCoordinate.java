package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate extends AbstractCoordinate{
    private final double phi, theta, radius;

    public SphericCoordinate(double phi, double theta, double radius) throws ValueOutOfRangeException {
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

    public double getPhi() {
        return phi;
    }
    public double getTheta() {
        return theta;
    }
    public double getRadius() {
        return radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws ValueOutOfRangeException {
        //assert class invariant
        assertClassInvariants();
        //pre-condition
        assertSphericValuesInRange(this);
        //method
        return new CartesianCoordinate(radius * Math.sin(theta)*Math.cos(phi),
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
