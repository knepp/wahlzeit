package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractCoordinate implements Coordinate {
    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();
    @Override
    public abstract SphericCoordinate asSphericCoordinate();


    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate coordinate1 = this.asCartesianCoordinate();
        CartesianCoordinate coordinate2 = coordinate.asCartesianCoordinate();
        if (coordinate2 == null)
            return -1;
        return Math.sqrt(Math.pow(coordinate1.getX() - coordinate2.getX(), 2) +
                Math.pow(coordinate1.getY() - coordinate2.getY(), 2) +
                Math.pow(coordinate1.getZ() - coordinate2.getZ(), 2));
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate c1 = this.asSphericCoordinate();
        SphericCoordinate c2 = coordinate.asSphericCoordinate();
        double deltaPhi = Math.abs(c2.getPhi() - c1.getPhi());
        double deltaT = Math.abs(c2.getTheta() - c1.getTheta());
        double cAng = 2* Math.asin(Math.sqrt(Math.pow(Math.sin(deltaPhi/2),2) +
                (1 - Math.pow(Math.sin(deltaPhi/2), 2) - Math.pow(Math.sin((c1.getPhi() + c2.getPhi())/2), 2)   ) *
                        Math.pow(Math.sin(deltaT/2), 2)));
        return cAng;
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        CartesianCoordinate coor = this.asCartesianCoordinate();
        rset.updateDouble("coordinate_x", coor.getX());
        rset.updateDouble("coordinate_y", coor.getY());
        rset.updateDouble("coordinate_z", coor.getZ());
    }

    @Override
    public boolean isEqual(Coordinate coor) {
        if (coor == null)
            return false;
        CartesianCoordinate c1 = this.asCartesianCoordinate();
        CartesianCoordinate c2 = coor.asCartesianCoordinate();
        double tolerance = 0.000001;
        double xDiff = Math.abs(c1.getX() - c2.getX());
        double yDiff = Math.abs(c1.getY() - c2.getY());
        double zDiff = Math.abs(c1.getZ() - c2.getZ());
        return xDiff < tolerance && yDiff < tolerance && zDiff < tolerance;
    }
}
