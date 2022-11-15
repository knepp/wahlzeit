package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate extends AbstractCoordinate{
    private final double phi, theta, radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = moduloAngle(phi);
        this.theta = moduloAngle(theta);
        this.radius = radius;
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
    public CartesianCoordinate asCartesianCoordinate() {
        return new CartesianCoordinate(radius * Math.sin(theta)*Math.cos(phi),
                radius * Math.sin(theta)*Math.sin(phi),
                radius * Math.cos(theta));
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /* disclaimer: method copied from http://www.java2s.com/example/java-utility-method/double-number-mod/moduloangle-double-angle-0a3b6.html
     * License: Open Source License
     */
    public static double moduloAngle(double angle) {
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
    }
}
