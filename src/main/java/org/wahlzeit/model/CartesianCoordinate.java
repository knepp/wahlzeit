package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static org.wahlzeit.model.SphericCoordinate.getSphericCoordinate;

public class CartesianCoordinate extends AbstractCoordinate {
    private static HashMap<Integer, CartesianCoordinate> cartesianHashMap;
    private final double x, y, z;

    private CartesianCoordinate(double x, double y, double z) {
        //no conditions as values for x y and z are not restricted
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
        int hash = hash(x,y,z);
        CartesianCoordinate c = cartesianHashMap.get(hash);
        //check if coordinate in hashMap
        if (c != null)
            return c;
        //coordinate does not exist yet -> create and put into hashMap
        c = new CartesianCoordinate(x,y,z);
        cartesianHashMap.put(hash, c);
        return c;
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



    private static int hash(double x, double y, double z) {
        return Objects.hash((int) (x/EPSILON), (int) (y/EPSILON), (int) (z/EPSILON));
    }

    @Override
    public boolean equals(Object obj) {
        //assert class invariant
        assertClassInvariants();
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
        //assert class invariant
        assertClassInvariants();
        //no pre-condition as this cannot be null and every coordinate has x, y and z, there is no other possibility
        return hash(x,y,z);
        //no post-condition needed
    }

    public static CartesianCoordinate readFrom(ResultSet rset) throws SQLException, IllegalArgumentException {
        //pre-condition rset not null
        if (rset == null)
            throw new IllegalArgumentException("Parameter ResultSet for CartesianCoordinate.readFrom was null.");
        //method
        return getCartesianCoordinate(rset.getDouble("coordinate_x"),
                rset.getDouble("coordinate_y"), rset.getDouble("coordinate_z"));
        //no post-condition needed as it can never return null and nothing else has to be checked
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        //assert class invariant
        assertClassInvariants();
        //no pre-condition needed
        return this;
        //no post-condition needed
    }


    @Override
    public SphericCoordinate asSphericCoordinate() throws ValueOutOfRangeException {
        //assert class invariant
        assertClassInvariants();
        //no pre-condition needed
        double r, t, p;
        r = Math.sqrt(x*x + y*y + z*z);
        t = SphericCoordinate.moduloAngle(Math.acos(z/r));
        p = SphericCoordinate.moduloAngle(Math.atan2(y, x));
        SphericCoordinate ret = getSphericCoordinate(p, t, r);
        //post-condition
        SphericCoordinate.assertSphericValuesInRange(ret);
        return ret;
    }

}
