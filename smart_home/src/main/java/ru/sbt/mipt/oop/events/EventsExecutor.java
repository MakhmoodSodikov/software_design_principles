package ru.sbt.mipt.oop.events;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;

public class EventsExecutor {
    private SmartHome smartHome;
    List<EventHandler> eventHandlers;
    EventGenerator eventGenerator;

    public EventsExecutor(SmartHome smartHome, List<EventHandler> eventHandlers, EventGenerator eventGenerator) {
        this.smartHome = smartHome;
        this.eventHandlers = eventHandlers;
        this.eventGenerator = eventGenerator;
    }

    public void startLoop(){
        SensorEvent event = eventGenerator.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler :eventHandlers) {
                handler.executeEvent(smartHome, event);
            }
            event = eventGenerator.getNextSensorEvent();
        }

    }
}
