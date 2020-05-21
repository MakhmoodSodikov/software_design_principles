package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public interface EventHandler {

    void executeEvent(SmartHome smartHome, SensorEvent event);

}
