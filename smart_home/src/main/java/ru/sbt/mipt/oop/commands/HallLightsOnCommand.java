package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.Door;
import ru.sbt.mipt.oop.interior.Room;

public class HallLightsOnCommand implements Command {
    private SmartHome smartHome;

    public HallLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(doorP -> {
                        if (doorP instanceof Door) {
                            ((Door) doorP).setState(false);
                        }
                    });
                }
            }
        });
    }
}