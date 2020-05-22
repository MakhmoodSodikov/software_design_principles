package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.types.SensorEventType;

import java.util.List;

public class AlarmNotificationHandler implements EventHandler {

    private final List<EventHandler> eventHandlers;

    public AlarmNotificationHandler(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent event) {
        if (isAlarmEvent(event)) {
            return;
        }

        if (smartHome.getAlarm().isOnWarning()) {
            System.out.println("Sending sms.");
            return;
        }

        eventHandlers.forEach(handler -> handler.executeEvent(smartHome, event));

        if (smartHome.getAlarm().isActivated()) {
            System.out.println("Sending sms.");
            smartHome.getAlarm().warning();
        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}