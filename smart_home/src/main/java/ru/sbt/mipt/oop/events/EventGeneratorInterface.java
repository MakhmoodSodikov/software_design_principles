package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.sensor.SensorEvent;

public interface EventGeneratorInterface {
    public SensorEvent getNextSensorEvent();
}
