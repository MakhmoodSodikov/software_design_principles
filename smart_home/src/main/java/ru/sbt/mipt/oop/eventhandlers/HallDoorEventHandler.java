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

    public HallDoorEventHandler() {
        
    }

    public void executeEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() != DOOR_OPEN & room.getName().equals("hall")) {
                            for (Room homeRoom : smartHome.getRooms()) {
                                for (Light light : homeRoom.getLights()) {
                                    light.setState(false);
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                    System.out.println("Pretend we're sending command " + command);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
