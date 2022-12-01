package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class AbstractCoordinate implements Coordinate {
    @Override
    public abstract CartesianCoordinate asCartesianCoordinate() throws ValueOutOfRangeException;
    @Override
    public abstract SphericCoordinate asSphericCoordinate() throws ValueOutOfRangeException;


    @Override
    public double getCartesianDistance(Coordinate coordinate) throws ValueOutOfRangeException, IllegalArgumentException {
        //assert class invariant
        assertClassInvariants();
        //test pre-condition
        assertCoordinateInputNotNull(coordinate);
        //method
        CartesianCoordinate coordinate1 = this.asCartesianCoordinate();
        CartesianCoordinate coordinate2 = coordinate.asCartesianCoordinate();
        if (coordinate2 == null)
            throw new IllegalArgumentException();
        double ret =    Math.sqrt(Math.pow(coordinate1.getX() - coordinate2.getX(), 2) +
                        Math.pow(coordinate1.getY() - coordinate2.getY(), 2) +
                        Math.pow(coordinate1.getZ() - coordinate2.getZ(), 2));
        //post-condition
        if (ret < 0)
            throw new ValueOutOfRangeException("distance cannot be less than 0. Exception in getCartesianDistance.");
        //assert class invariant
        assertClassInvariants();
        return ret;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) throws ValueOutOfRangeException, IllegalArgumentException {
        //assert class invariant
        assertClassInvariants();
        //test pre-condition
        assertCoordinateInputNotNull(coordinate);
        //method
        SphericCoordinate c1 = this.asSphericCoordinate();
        SphericCoordinate c2 = coordinate.asSphericCoordinate();
        double deltaPhi = Math.abs(c2.getPhi() - c1.getPhi());
        double deltaT = Math.abs(c2.getTheta() - c1.getTheta());
        double cAng = 2* Math.asin(Math.sqrt(Math.pow(Math.sin(deltaPhi/2),2) +
                (1 - Math.pow(Math.sin(deltaPhi/2), 2) - Math.pow(Math.sin((c1.getPhi() + c2.getPhi())/2), 2)   ) *
                        Math.pow(Math.sin(deltaT/2), 2)));
        //post-condition
        if(cAng < 0)
            throw new ValueOutOfRangeException("angle cannot be less than 0. Exception in getCentralAngle");
        //assert class invariant
        assertClassInvariants();
        return cAng;
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException, IllegalArgumentException, ValueOutOfRangeException {
        //assert class invariant
        assertClassInvariants();
        //pre-condition
        if (rset == null)
            throw new IllegalArgumentException("Parameter ResultSet in writeOn was null.");
        //method
        CartesianCoordinate coor = this.asCartesianCoordinate();
        rset.updateDouble("coordinate_x", coor.getX());
        rset.updateDouble("coordinate_y", coor.getY());
        rset.updateDouble("coordinate_z", coor.getZ());
        //no post-condition
        //assert class invariant
        assertClassInvariants();
    }

    @Override
    public boolean isEqual(Coordinate coor) throws ValueOutOfRangeException {
        //assert class invariant
        assertClassInvariants();
        //no pre-condition as coor can perfectly well be null
        if (coor == null)
            return false;
        CartesianCoordinate c1 = this.asCartesianCoordinate();
        CartesianCoordinate c2 = coor.asCartesianCoordinate();
        double tolerance = 0.000001;
        double xDiff = Math.abs(c1.getX() - c2.getX());
        double yDiff = Math.abs(c1.getY() - c2.getY());
        double zDiff = Math.abs(c1.getZ() - c2.getZ());
        boolean ret = xDiff < tolerance && yDiff < tolerance && zDiff < tolerance;
        //no post-condition
        //assert class invariant
        assertClassInvariants();
        return ret;
    }

    protected static void assertCoordinateInputNotNull(Coordinate coordinate) throws IllegalArgumentException {
        if (coordinate == null)
            throw new IllegalArgumentException("Parameter coordinate was null.");
    }

    protected static void assertClassInvariants() throws IllegalArgumentException{
        //there is no invariant for Coordinate, so this function is empty.
    }
}
