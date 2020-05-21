package ru.sbt.mipt.oop.interior;

public class Door extends InteriorElement {
    private final String id;
    private final String elementType;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
        this.elementType = "Door";
    }

    @Override
    public void setState(boolean open) {
        isOpen = open;
    }
}
