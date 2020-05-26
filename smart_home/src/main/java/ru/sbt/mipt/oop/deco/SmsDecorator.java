package ru.sbt.mipt.oop.deco;

import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.alarm.Alarm;
import ru.sbt.mipt.oop.interior.alarm.states.ActivatedAlarmState;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.types.SensorEventType;

public class SmsDecorator implements EventHandler {

    private EventHandler eventHandler;

    public SmsDecorator(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        Alarm alarm = smartHome.getAlarm();
        if (sensorEvent.getType() == SensorEventType.LIGHT_ON || sensorEvent.getType() == SensorEventType.LIGHT_OFF ||
                sensorEvent.getType() == SensorEventType.DOOR_OPEN || sensorEvent.getType() == SensorEventType.DOOR_CLOSED) {
            if (alarm.getState() instanceof ActivatedAlarmState) {
                System.out.println("SMS");
            }
            return;
        }
    }
}
