package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
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

}
