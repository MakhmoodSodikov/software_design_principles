package ru.sbt.mipt.oop.interior;

public abstract class InteriorElement {
    private String id;
    private boolean isOpen;
    private String elementType;
    public String name;

    public abstract void setState(boolean state);

    public String getElementType() {
        return elementType;
    }

    public String getId() {
        return id;
    }
}
