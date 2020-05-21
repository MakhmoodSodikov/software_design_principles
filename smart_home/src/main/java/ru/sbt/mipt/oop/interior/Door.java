package ru.sbt.mipt.oop.interior;

import ru.sbt.mipt.oop.action.Action;

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

    @Override
    public void execute(Action action) {
        action.execute(this);
    }

}
