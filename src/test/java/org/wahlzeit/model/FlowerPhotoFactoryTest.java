package org.wahlzeit.model;

import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import java.sql.ResultSet;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class FlowerPhotoFactoryTest {
    @Test
    public void createFlowerPhotoFactoryTest() {
        PhotoFactory phF = PhotoFactory.getInstance();
        assert (phF instanceof FlowerPhotoFactory);
    }
    @Test
    public void singletonTest() {
        PhotoFactory phF1 = PhotoFactory.getInstance();
        PhotoFactory phF2 = PhotoFactory.getInstance();
        assert(phF1 == phF2);
    }
    @Test
    public void setInstanceTest() {
        PhotoFactory phF = PhotoFactory.getInstance();
        FlowerPhotoFactory.setInstance(phF);
        assert (phF == FlowerPhotoFactory.getInstance());
    }

    @Test
    public void createPhoto() {
        FlowerPhotoFactory phF = FlowerPhotoFactory.getInstance();
        FlowerPhoto p1 = phF.createPhoto();
        FlowerPhoto p2 = phF.createPhoto(new PhotoId(123));
        assertNotNull(p1);
        assertNotNull(p2);
    }
}
