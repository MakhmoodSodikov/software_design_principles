package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.smarthome.CCEventHandler;
import ru.sbt.mipt.oop.smarthome.CCSensorEvent;
import ru.sbt.mipt.oop.types.SensorEventType;

import java.util.Map;

public class CCEventHandlerAdapter implements CCEventHandler {
    private final EventHandler eventHandler;
    private final SmartHome smartHome;
    private final Map<String, SensorEventType> ccEventTypeToEventTypeMap;

    public CCEventHandlerAdapter(EventHandler eventHandler, SmartHome smartHome, Map<String, SensorEventType> ccEventTypeToEventTypeMap) {
        this.eventHandler = eventHandler;
        this.smartHome = smartHome;
        this.ccEventTypeToEventTypeMap = ccEventTypeToEventTypeMap;
    }

    @Override
    public void executeEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEvent(
                ccEventTypeToEventTypeMap.getOrDefault(event.getEventType(), SensorEventType.UNDEFINED_EVENT),
                event.getObjectId()
        );

        eventHandler.executeEvent(smartHome, sensorEvent);
    }
}