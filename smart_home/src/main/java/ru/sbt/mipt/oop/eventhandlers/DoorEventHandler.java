package ru.sbt.mipt.oop.eventhandlers;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.Door;
import ru.sbt.mipt.oop.interior.Room;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import static ru.sbt.mipt.oop.types.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.types.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler{
    public DoorEventHandler() {

    }

    public void executeEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        validateState(event, door, room);
                    }
                }
            }
        }
    }

    private void validateState(SensorEvent event, Door door, Room room) {
        if (event.getType() == DOOR_OPEN) {
            door.setState(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            door.setState(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        }
    }
}
