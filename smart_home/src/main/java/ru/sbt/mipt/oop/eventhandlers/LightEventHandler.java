package ru.sbt.mipt.oop.eventhandlers;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.Light;
import ru.sbt.mipt.oop.interior.Room;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import static ru.sbt.mipt.oop.types.SensorEventType.*;

public class LightEventHandler implements EventHandler{

    public LightEventHandler() { }

    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent event) {

        if (!isLightEvent(event)) {
            return;
        }

        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Light) {
                        Light light = (Light) roomComponent;

                        if (light.getId().equals(event.getObjectId())) {
                            validateState(event, light, room);
                        }
                    }
                });
            }
        });
    }

    private void validateState(SensorEvent event, Light light, Room room) {
        if (event.getType() == LIGHT_ON) {
            light.setState(true);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            light.setState(false);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }
}
