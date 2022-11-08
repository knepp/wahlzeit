package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org. mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoTest {
    @Test
    public void testCreateNoLocation() {
        Photo photo1a = new Photo();
        Assert.assertNotNull(photo1a);
        Assert.assertNull(photo1a.location);

        Location nullLocation = null;
        Photo photo1b = new Photo(nullLocation);
        Assert.assertNotNull(photo1b);
        Assert.assertNull(photo1b.location);

        PhotoId id_a = PhotoId.getNextId();   //can also be null for this test
        Photo photo2a = new Photo(id_a);
        //test if photo created with location and id
        Assert.assertNotNull(photo2a);
        Assert.assertNull(photo2a.location);
        Assert.assertNotNull(photo2a.id);
        Assert.assertEquals(id_a.value, photo2a.id.value);

        PhotoId id_b = PhotoId.getNextId();   //can also be null for this test
        Photo photo2b = new Photo(id_b, null);
        //test if photo created with location and id
        Assert.assertNotNull(photo2b);
        Assert.assertNull(photo2b.location);
        Assert.assertNotNull(photo2b.id);
        Assert.assertEquals(id_b.value, photo2b.id.value);
    }
    @Test
    public void testCreateWithLocation() {
        CartesianCoordinate coor1 = new CartesianCoordinate(1.2,3.4,5.6);
        Location location1 = new Location(coor1);
        Photo photo1 = new Photo(location1);
        Assert.assertNotNull(photo1.location);
        // test if connection from photo to location to coordinate ist there with all values
        Assert.assertEquals(1.2, photo1.location.coordinate.asCartesianCoordinate().getX(), 0.0);
        Assert.assertEquals(3.4, photo1.location.coordinate.asCartesianCoordinate().getY(), 0.0);
        Assert.assertEquals(5.6, photo1.location.coordinate.asCartesianCoordinate().getZ(), 0.0);


        CartesianCoordinate coor2 = new CartesianCoordinate(7.8,9.0,10.11);
        Location location2 = new Location(coor2);
        PhotoId id = PhotoId.getNextId();   //can also be null for this test
        Photo photo2 = new Photo(id, location2);
        Assert.assertNotNull(photo2.location);
        // test if connection from photo to location to coordinate ist there with all values
        Assert.assertNotNull(photo2.location.coordinate);
        Assert.assertEquals(7.8, photo2.location.coordinate.asCartesianCoordinate().getX(), 0.0);
        Assert.assertEquals(9.0, photo2.location.coordinate.asCartesianCoordinate().getY(), 0.0);
        Assert.assertEquals(10.11, photo2.location.coordinate.asCartesianCoordinate().getZ(), 0.0);
        //test if id is correct
        Assert.assertNotNull(photo2.id);
        Assert.assertEquals(id.value, photo2.id.value);
    }
    @Test
    public void testWriteOn() throws SQLException {
        CartesianCoordinate coor = new CartesianCoordinate(4,5,6);
        Location loc = new Location(coor);
        ResultSet rset = Mockito.mock(ResultSet.class);
        loc.writeOn(rset);
        verify(rset, Mockito.times(1)).updateDouble("coordinate_x", coor.getX());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_y", coor.getY());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_z", coor.getZ());
    }
    @Test
    public void testReadFrom() throws SQLException {
        CartesianCoordinate coor = new CartesianCoordinate(4,5,6);
        Location loc = new Location(coor);
        ResultSet rset = Mockito.mock(ResultSet.class);
        Location.readFrom(rset);
        verify(rset, Mockito.times(1)).getDouble("coordinate_x");
        verify(rset, Mockito.times(1)).getDouble("coordinate_y");
        verify(rset, Mockito.times(1)).getDouble("coordinate_z");
    }
}
