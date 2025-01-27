package org.wahlzeit.model;

import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.services.DataObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordinate() throws ValueOutOfRangeException;
    double getCartesianDistance(Coordinate coordinate) throws ValueOutOfRangeException, IllegalArgumentException;
    SphericCoordinate asSphericCoordinate() throws ValueOutOfRangeException;
    double getCentralAngle(Coordinate coordinate) throws ValueOutOfRangeException, IllegalArgumentException;
    boolean isEqual(Coordinate coordinate) throws ValueOutOfRangeException;

    @PatternInstance(
            patternName="Chain of Responsibility Pattern",
            participants = {"handler"}
    )
    void writeOn(ResultSet rset) throws SQLException, ValueOutOfRangeException, IllegalArgumentException;
}
