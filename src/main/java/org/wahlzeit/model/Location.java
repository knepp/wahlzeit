package org.wahlzeit.model;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location {
    public Coordinate coordinate;

    public Location (Coordinate coordinate) throws InvalidParameterException {
        if (coordinate == null) {
            throw new InvalidParameterException();
        }
        this.coordinate = coordinate;
    }
    public static Location readFrom(ResultSet rset) throws SQLException {
        return new Location(Coordinate.readFrom(rset));
    }
    public void writeOn(ResultSet rset) throws SQLException {
        this.coordinate.writeOn(rset);
    }
}
