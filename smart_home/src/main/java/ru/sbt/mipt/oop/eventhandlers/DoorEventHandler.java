package ru.sbt.mipt.oop.eventhandlers;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.Door;
import ru.sbt.mipt.oop.interior.Room;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import static ru.sbt.mipt.oop.types.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.types.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler{
    public DoorEventHandler() { }

    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent event) {

        if (!isDoorEvent(event)) {
            return;
        }

        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Door) {
                        Door door = (Door) roomComponent;

                        if (door.getId().equals(event.getObjectId())) {
                            this.validateState(event, door, room);
                        }
                    }
                });
            }
        });
    }

    private boolean isDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
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
