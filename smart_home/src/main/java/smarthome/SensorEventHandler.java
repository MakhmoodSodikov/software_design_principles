package smarthome;


import java.util.ArrayList;
import java.util.Collection;

public class SensorEventHandler {
    private final String[] eventTypes = new String[] { "LightIsOn", "LightIsOff", "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked" };

    private Collection<CCEventHandler> handlers = new ArrayList<>();

    public void registerEventHandler(CCEventHandler handler) {
        this.handlers.add(handler);
    }

    public void start() {
        CCSensorEvent event = getNextSensorEvent();
        while (event != null) {
            for (CCEventHandler handler : handlers) {
                handler.executeEvent(event);
            }
            event = getNextSensorEvent();
        }
    }

    private CCSensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        String sensorEventType = eventTypes[(int) (6 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new CCSensorEvent(sensorEventType, objectId);
    }


}

