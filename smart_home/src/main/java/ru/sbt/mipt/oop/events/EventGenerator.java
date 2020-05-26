package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.sensor.SensorEvent;

public interface EventGenerator {
    public SensorEvent getNextSensorEvent();
}
