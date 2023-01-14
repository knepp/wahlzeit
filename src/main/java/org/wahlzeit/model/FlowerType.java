package org.wahlzeit.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Flow;

public class FlowerType {
    private final String name;  //names don't change, they're named once
    private FlowerType superType = null;
    private LinkedList<FlowerType> subTypes;
    protected FlowerType(String name) {
        this.name = name;
        subTypes = new LinkedList<>();
    }

    protected void setSuperType(FlowerType type) {
        superType = type;
        type.subTypes.add(this);
    }
    public FlowerType getSuperType() {
        return superType;
    }

    public void addSubtype(FlowerType type) {
        this.subTypes.add(type);
        type.superType = this;
    }

    protected Flower createInstance(){
        return new Flower(this);
    }

    /***
     * determines whether this type is the type of the flower - taken from lecture slides
     * @param flower
     * @return boolean whether this type is type of flower
     */
    public boolean isOfTypeOrSubtype (Flower flower) {
        assert (flower != null) : "asked about null object";
        if (flower.getType() == this)
            return true;
        for (FlowerType type : subTypes) {
            if (type.isOfTypeOrSubtype(flower))
                return true;
        }
        return false;
    }

    public boolean isSubtype(FlowerType inType) {
        assert (inType != null) : "asked about null object";
        if (this == inType)
            return false;
        return isSubtypeHelper(inType);
    }
    public boolean isSubtypeHelper(FlowerType inType) {
        if (this == inType)
            return true;
        for (FlowerType type : subTypes) {
            if (type.isSubtypeHelper(inType))
                return true;
        }
        return false;
    }


    public String getName() {
        return name;
    }
}
