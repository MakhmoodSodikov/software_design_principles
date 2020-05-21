package ru.sbt.mipt.oop.events;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;

public class EventsExecutor {
    private SmartHome smartHome;
    List<EventHandler> eventHandlers;
    public EventsExecutor(SmartHome smartHome, List<EventHandler> eventHandlers) {
        this.smartHome = smartHome;
        this.eventHandlers = eventHandlers;
    }

    public void startLoop(){
        EventGenerator eventGenerator = new EventGenerator();
        SensorEvent event = eventGenerator.getNextSensorEvent();
        EventManager eventManager = new EventManager(smartHome, eventHandlers);

        while (event != null) {
            System.out.println("Got event: " + event);
            eventManager.manageEvent(event);
            event = eventGenerator.getNextSensorEvent();
        }

    }
}
