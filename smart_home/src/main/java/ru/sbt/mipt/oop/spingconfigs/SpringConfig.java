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
import ru.sbt.mipt.oop.eventhandlers.AlarmNotificationHandler;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.loaders.HomeDataLoader;
import ru.sbt.mipt.oop.loaders.HomeJsonDataLoader;
import ru.sbt.mipt.oop.smarthome.SensorEventHandler;
import ru.sbt.mipt.oop.types.SensorEventType;


@Configuration
@Import(SpringEventHandlerConfiguration.class)
@PropertySource("application.properties")
public class SpringConfig {

    @Bean
    public Map<String, SensorEventType> ccEventTypeToEventTypeMap() {
        return new HashMap<String, SensorEventType>() {{
            put("LightIsOn", SensorEventType.LIGHT_ON);
            put("LightIsOff", SensorEventType.LIGHT_OFF);
            put("DoorIsOpen", SensorEventType.DOOR_OPEN);
            put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        }};
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
                                                       SmartHome smartHome, Map<String, SensorEventType> ccEventTypeToEventTypeMap) {
        SensorEventHandler sensorEventsManager = new SensorEventHandler();

        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(
                        new AlarmNotificationHandler(eventHandlers),
                        smartHome,
                        ccEventTypeToEventTypeMap
                )
        );

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
        return new AlarmDecorator(new EventDoorHandler());
    }
}
