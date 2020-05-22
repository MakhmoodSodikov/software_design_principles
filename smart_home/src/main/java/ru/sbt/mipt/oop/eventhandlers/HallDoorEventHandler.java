package ru.sbt.mipt.oop.eventhandlers;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.Door;
import ru.sbt.mipt.oop.interior.Light;
import ru.sbt.mipt.oop.interior.Room;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.types.CommandType;

import static ru.sbt.mipt.oop.types.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.types.SensorEventType.DOOR_OPEN;

public class HallDoorEventHandler implements EventHandler{

    public HallDoorEventHandler() { }

    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent event) {
        if (!isHallDoorEvent(event)) {
            return;
        }

        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                if (room.getName().equals("hall")) {
                    room.execute(roomComponent -> {
                        if (roomComponent instanceof Door) {
                            Door door = (Door) roomComponent;

                            if (door.getId().equals(event.getObjectId())) {
                                validateState(smartHome);
                            }
                        }
                    });
                }
            }
        });
    }

    private void validateState(SmartHome smartHome) {
        smartHome.execute(component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                light.setState(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());

            }
        });
    }

    private boolean isHallDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
