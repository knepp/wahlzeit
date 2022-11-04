package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

public class FlowerPhotoManagerTest {
    @Test
    public void getInstanceTest() {
        PhotoManager fMan = PhotoManager.getInstance();
        assertNotNull(fMan);
        assert (fMan instanceof FlowerPhotoManager);
    }
}
