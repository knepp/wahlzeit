package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {
    private final double x, y, z;

    public CartesianCoordinate(double x, double y, double z) {
        //no conditions as values for x y and z are not restricted
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
        //no pre-condition needed as all objects can be worked with
        if (obj == this)
            return true;
        if (! (obj instanceof Coordinate))  //checks for null, too
            return false;
        try {
            return this.isEqual((Coordinate) obj);
        } catch (ValueOutOfRangeException e) {  //only exception allowed
            throw new RuntimeException(e);
        }
    }
    @Override
    public int hashCode() {
        //no pre-condition as this cannot be null and every coordinate has x, y and z, there is no other possibility
        return Objects.hash(x, y, z);
        //no post-condition needed
    }

    public static CartesianCoordinate readFrom(ResultSet rset) throws SQLException {
        //pre-condition rset not null
        if (rset == null)
            throw new IllegalArgumentException();
        //method
        return new CartesianCoordinate(rset.getDouble("coordinate_x"),
                rset.getDouble("coordinate_y"), rset.getDouble("coordinate_z"));
        //no post-condition needed as it can never return null and nothing else has to be checked
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        //no pre-condition needed
        return this;
        //no post-condition needed
    }


    @Override
    public SphericCoordinate asSphericCoordinate() throws ValueOutOfRangeException {
        //no pre-condition needed
        double r, t, p;
        r = Math.sqrt(x*x + y*y + z*z);
        t = SphericCoordinate.moduloAngle(Math.acos(z/r));
        p = SphericCoordinate.moduloAngle(Math.atan2(y, x));
        SphericCoordinate ret = new SphericCoordinate(p, t, r);
        //post-condition
        SphericCoordinate.assertSphericValuesInRange(ret);
        return ret;
    }

}
