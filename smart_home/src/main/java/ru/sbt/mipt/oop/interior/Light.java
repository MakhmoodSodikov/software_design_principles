package ru.sbt.mipt.oop.interior;

public class Light extends InteriorElement {
    private final String id;
    private boolean isOn;
    private final String elementType;

    public Light(String id, boolean isOn) {
        this.isOn = isOn;
        this.id = id;
        this.elementType = "Light";
    }

    @Override
    public void setState(boolean on) {
        isOn = on;
    }
}
