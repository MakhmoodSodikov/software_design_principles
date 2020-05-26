package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.types.SensorEventType;

public class AlarmEventHandler implements EventHandler {
    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent event) {

        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getAlarm().activate(event.getObjectId());
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getAlarm().deactivate(event.getObjectId());
        }
    }
}
