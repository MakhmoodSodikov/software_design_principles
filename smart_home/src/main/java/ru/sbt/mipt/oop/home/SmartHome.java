package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;
import ru.sbt.mipt.oop.interior.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {

    @Override
    public void execute(Action action) {
        action.execute(this);

        rooms.forEach(room -> room.execute(action));
    }

    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
