package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Coordinate {
    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
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

    public double getDistance(Coordinate coor) {
        if (coor == null)
            return -1;
        return Math.sqrt(Math.pow(x - coor.getX(), 2) +
                Math.pow(y - coor.getY(), 2) +
                Math.pow(z - coor.getZ(), 2));
    }
    public boolean isEqual(Coordinate coor) {
        if (coor == null)
            return false;
        return x == coor.getX() && y == coor.getY() && z == coor.getZ();
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
    public static Coordinate readFrom(ResultSet rset) throws SQLException {
        return new Coordinate(rset.getDouble("coordinate_x"),
                rset.getDouble("coordinate_y"), rset.getDouble("coordinate_z"));
    }

    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("coordinate_x", x);
        rset.updateDouble("coordinate_y", y);
        rset.updateDouble("coordinate_z", z);
    }
}
