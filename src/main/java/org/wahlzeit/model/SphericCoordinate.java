package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate implements Coordinate{
    private double phi, theta, radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi % Math.PI;
        this.theta = theta % Math.PI;
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
    public double getCartesianDistance(Coordinate coordinate) {
        return this.asCartesianCoordinate().getCartesianDistance(coordinate.asCartesianCoordinate());
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {//TODO
        SphericCoordinate c = coordinate.asSphericCoordinate();
        double deltaPhi = Math.abs(c.phi - this.phi);
        double deltaT = Math.abs(c.theta - this.theta);
        double cAng = 2* Math.asin(Math.sqrt(Math.pow(Math.sin(deltaPhi/2),2) +
                (1 - Math.pow(Math.sin(deltaPhi/2), 2) - Math.pow(Math.sin((this.phi + c.phi)/2), 2)   ) *
                Math.pow(Math.sin(deltaT/2), 2)));
        return cAng;
    }

    public boolean isEqual(SphericCoordinate coor) {
        double tolerance = 0.000001;
        double pDiff = Math.abs(phi - coor.phi);
        double tDiff = Math.abs(theta - coor.theta);
        double rDiff = Math.abs(radius - coor.radius);
        return pDiff < tolerance && tDiff < tolerance && rDiff < tolerance;
    }
    public boolean isEqual(Coordinate coordinate) {
        return this.isEqual(coordinate.asSphericCoordinate());
    }
    public void writeOn(ResultSet rset) throws SQLException {
        CartesianCoordinate coor = this.asCartesianCoordinate();
        rset.updateDouble("coordinate_x", coor.getX());
        rset.updateDouble("coordinate_y", coor.getY());
        rset.updateDouble("coordinate_z", coor.getZ());
    }
}
