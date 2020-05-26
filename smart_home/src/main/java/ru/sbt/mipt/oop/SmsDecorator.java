package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.alarm.Alarm;
import ru.sbt.mipt.oop.interior.alarm.states.ActivatedAlarmState;
import ru.sbt.mipt.oop.interior.alarm.states.WarningModeAlarmState;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;

import static ru.sbt.mipt.oop.types.SensorEventType.*;

public class SmsDecorator implements EventHandler {

    private List<EventHandler> eventHandlers;

    public SmsDecorator(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        Alarm alarm = smartHome.getAlarm();
        if (alarm.getState() instanceof WarningModeAlarmState) {
            System.out.println("SMS");
            return;
        }
        eventHandlers.forEach(eventHandler -> {
            eventHandler.executeEvent(smartHome,sensorEvent);
        });
        if (alarm.getState() instanceof ActivatedAlarmState){
            alarm.warning();
            System.out.println("SMS");
        }

    }
    private boolean isLightOrDoorEvent(SensorEvent sensorEvent){
        return sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF ||
                sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED;
    }

}