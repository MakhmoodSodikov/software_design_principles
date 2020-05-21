package ru.sbt.mipt.oop.events;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import java.util.List;


public class EventManager {

//    private CommandSender commandSender = new CommandSender();
//    private CustomLogger logger = new CustomLogger();
//    private CustomLogger logger = CustomLogger();

    private SmartHome smartHome;
    private final List<EventHandler> eventHandlers;

    public EventManager(SmartHome smartHome, List<EventHandler> eventHandlers) {
        this.smartHome = smartHome;
        this.eventHandlers = eventHandlers;
    }

    public void manageEvent(SensorEvent event) {
        for (EventHandler handler :eventHandlers) {
            handler.executeEvent(smartHome, event);
        }

    }
}
