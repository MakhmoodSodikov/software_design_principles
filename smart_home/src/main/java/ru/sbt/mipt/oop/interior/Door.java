package ru.sbt.mipt.oop.interior;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

public class Door implements Actionable {
    private final String id;
    private final String elementType;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
        this.elementType = "Door";
    }

    public String getId() {
        return id;
    }

    public boolean getState() {
        return isOpen;
    }

    public void setState(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }

}
