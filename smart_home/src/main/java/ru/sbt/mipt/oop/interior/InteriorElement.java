package ru.sbt.mipt.oop.interior;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

public abstract class InteriorElement implements Actionable {
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

    @Override
    public void execute(Action action) {
        action.execute(this);
    }

}
