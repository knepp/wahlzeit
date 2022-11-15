package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {
    private final double x, y, z;

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

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {
        double r, t, p;
        r = Math.sqrt(x*x + y*y + z*z);
        t = SphericCoordinate.moduloAngle(Math.acos(z/r));
        p = SphericCoordinate.moduloAngle(Math.atan2(y, x));
        return new SphericCoordinate(p, t, r);
    }
}
