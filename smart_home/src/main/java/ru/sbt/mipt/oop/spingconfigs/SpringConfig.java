package ru.sbt.mipt.oop.spingconfigs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.sbt.mipt.oop.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.deco.AlarmDecorator;
import ru.sbt.mipt.oop.deco.SmsDecorator;
import ru.sbt.mipt.oop.eventhandlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.loaders.HomeDataLoader;
import ru.sbt.mipt.oop.loaders.HomeJsonDataLoader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import smarthome.SensorEventHandler;
import ru.sbt.mipt.oop.types.SensorEventType;


@Configuration
@Import(SpringEventHandlerConfiguration.class)
@PropertySource("application.properties")
public class SpringConfig {

    @Bean
    public Map<String, SensorEventType> ccEventTypeToEventTypeMap() {
        HashMap<String, SensorEventType> map = new HashMap<String, SensorEventType>();
        map.put("LightIsOn", SensorEventType.LIGHT_ON);
        map.put("LightIsOff", SensorEventType.LIGHT_OFF);
        map.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        map.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);

        return map;
    }

    @Bean
    public HomeDataLoader smartHomeLoader(@Value("${smarthome.json.filename}") String filename) {
        return new HomeJsonDataLoader(filename);
    }

    @Bean
    @Autowired
    public SmartHome smartHome(HomeDataLoader smartHomeLoader) {
        return smartHomeLoader.loadData();
    }

    @Bean
    @Autowired
    public SensorEventHandler sensorEventsManager(List<EventHandler> eventHandlers,
                                                       SmartHome smartHome, Map<String, SensorEventType> eventTypeMap) {
        SensorEventHandler sensorEventsManager = new SensorEventHandler();

        eventHandlers.forEach(eventHandler -> {
            sensorEventsManager.registerEventHandler(new CCEventHandlerAdapter(eventHandler, smartHome, eventTypeMap));
        });

        return sensorEventsManager;

    }

    @Bean
    EventHandler alarmEventHandler() {
        return new SmsDecorator(new AlarmEventHandler());
    }

    @Bean
    EventHandler lightEventHandler() {
        return new AlarmDecorator(new LightEventHandler());
    }

    @Bean
    EventHandler eventDoorHandler() {
        return new AlarmDecorator(new DoorEventHandler());
    }
}
