package ru.sbt.mipt.oop.interior;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

public class Light implements Actionable{
    private final String id;
    private boolean isOn;
    private final String elementType;

    public Light(String id, boolean isOn) {
        this.isOn = isOn;
        this.id = id;
        this.elementType = "Light";
    }

    public String getId() {
        return id;
    }

    public boolean getState() {
        return isOn;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }

    public void setState(boolean on) {
        isOn = on;
    }
}
