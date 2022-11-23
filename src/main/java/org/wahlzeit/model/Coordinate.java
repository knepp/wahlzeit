package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordinate() throws ValueOutOfRangeException;
    double getCartesianDistance(Coordinate coordinate) throws ValueOutOfRangeException;
    SphericCoordinate asSphericCoordinate() throws ValueOutOfRangeException;
    double getCentralAngle(Coordinate coordinate) throws ValueOutOfRangeException;
    boolean isEqual(Coordinate coordinate) throws ValueOutOfRangeException;
    void writeOn(ResultSet rset) throws SQLException, ValueOutOfRangeException;
}
