package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CartesianCoordinate implements Coordinate {
    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }




    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (! (obj instanceof Coordinate))  //checks for null, too
            return false;
        return this.isEqual((Coordinate) obj);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public static CartesianCoordinate readFrom(ResultSet rset) throws SQLException {
        return new CartesianCoordinate(rset.getDouble("coordinate_x"),
                rset.getDouble("coordinate_y"), rset.getDouble("coordinate_z"));
    }

    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("coordinate_x", x);
        rset.updateDouble("coordinate_y", y);
        rset.updateDouble("coordinate_z", z);
    }

    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate coor = coordinate.asCartesianCoordinate();
        if (coor == null)
            return -1;
        return Math.sqrt(Math.pow(x - coor.getX(), 2) +
                Math.pow(y - coor.getY(), 2) +
                Math.pow(z - coor.getZ(), 2));
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {
        double r, t, p;
        r = Math.sqrt(x*x + y*y + z*z);
        t = (Math.acos(z/r)) % Math.PI;
        p = (Math.atan2(y, x)) % Math.PI;
        return new SphericCoordinate(p, t, r);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return this.asSphericCoordinate().getCentralAngle(coordinate.asSphericCoordinate());
    }

    public boolean isEqual(CartesianCoordinate coor) {
        if (coor == null)
            return false;
        double tolerance = 0.000001;
        double xDiff = Math.abs(x - coor.x);
        double yDiff = Math.abs(y - coor.y);
        double zDiff = Math.abs(z - coor.z);
        return xDiff < tolerance && yDiff < tolerance && zDiff < tolerance;
    }
    public boolean isEqual(Coordinate coor) {
        return this.isEqual(coor.asCartesianCoordinate());
    }
}
