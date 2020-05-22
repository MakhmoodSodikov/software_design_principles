package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;
import ru.sbt.mipt.oop.interior.Room;
import ru.sbt.mipt.oop.interior.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {

    @Override
    public void execute(Action action) {
        action.execute(this);

        rooms.forEach(room -> room.execute(action));
    }

    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Alarm alarm, Collection<Room> rooms) {
        this.rooms = rooms;
        this.alarm = alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Alarm getAlarm() {
        return alarm;
    }
}
