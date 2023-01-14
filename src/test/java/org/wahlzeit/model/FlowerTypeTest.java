package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlowerTypeTest {
    @Test
    public void testGetSetSuperType() {
        FlowerManager fm = FlowerManager.getInstance();
        FlowerType f1 = fm.getFlowerType("orchidTest");
        FlowerType f2 = fm.getFlowerType("superOrchidTest");
        assertNotSame(f1.getSuperType(), f2);
        f1.setSuperType(f2);
        assertSame(f1.getSuperType(), f2);
    }

    @Test
    public void testAddSubtype() {
        FlowerManager fm = FlowerManager.getInstance();
        FlowerType fSub = fm.getFlowerType("orchid");
        FlowerType fSup = fm.getFlowerType("superOrchid");
        fSup.addSubtype(fSub);
        assertSame(fSup, fSub.getSuperType());
    }

    @Test
    public void testIsSubtype() {
        FlowerManager fm = FlowerManager.getInstance();
        FlowerType f1 = fm.getFlowerType("orchid");
        FlowerType f2 = fm.getFlowerType("superOrchid");
        assertFalse(f1.isSubtype(f2));
        f1.setSuperType(f2);
        assertTrue(f2.isSubtype(f1));
    }

    @Test
    public void testGetName() {
        FlowerManager fm = FlowerManager.getInstance();
        FlowerType ft = fm.getFlowerType("orchid");
        assertEquals("orchid", ft.getName());
    }

    @Test
    public void testIsOfTypeOrSubtype() {
        FlowerManager fm = FlowerManager.getInstance();
        FlowerType fSup = fm.getFlowerType("superOrchid");
        FlowerType fSub = fm.getFlowerType("subOrchid");
        FlowerType fSub2 = fm.getFlowerType("sub2orchid");
        FlowerType fSub3 = fm.getFlowerType("sub3orchid");
        FlowerType fSub4 = fm.getFlowerType("sub4orchid");
        FlowerType fSub25 = fm.getFlowerType("sub2.5orchid");
        fSub4.setSuperType(fSub3);
        fSub3.setSuperType(fSub2);
        fSub2.setSuperType(fSub);
        fSub.setSuperType(fSup);
        fSub25.setSuperType(fSub3);

        Flower flower = fm.createFlower("sub4orchid");
        assertSame(fSub4, flower.getType());
        assertTrue(fSup.isOfTypeOrSubtype(flower));

        Flower flower2 = fm.createFlower("sub2.5orchid");
        assertTrue(fSup.isOfTypeOrSubtype(flower2));
        assertTrue(fSub25.isOfTypeOrSubtype(flower2));
        assertFalse(fSub4.isOfTypeOrSubtype(flower2));



    }
}
