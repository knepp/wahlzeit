package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordinate();
    double getCartesianDistance(Coordinate coordinate);
    SphericCoordinate asSphericCoordinate();
    double getCentralAngle(Coordinate coordinate);
    boolean isEqual(Coordinate coordinate);
    void writeOn(ResultSet rset) throws SQLException;
}
