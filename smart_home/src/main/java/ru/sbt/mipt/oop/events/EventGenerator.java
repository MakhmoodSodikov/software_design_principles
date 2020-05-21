package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.types.SensorEventType;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public class EventGenerator {
    public EventGenerator() { }

    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

}
