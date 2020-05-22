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
import ru.sbt.mipt.oop.RemoteControlImpl;
import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.eventhandlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.loaders.HomeDataLoader;
import ru.sbt.mipt.oop.loaders.HomeJsonDataLoader;
import ru.sbt.mipt.oop.smarthome.SensorEventHandler;
import ru.sbt.mipt.oop.smarthome.remote.RemoteControlRegistry;
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
        return new AlarmDecorator(new DoorEventHandler());
    }

    @Bean
    @Autowired
    public AlarmActivateCommand activateAlarmCommand(
            SmartHome smartHome,
            CommandSender commandSender,
            @Value("${smarthome.alarm.code}") String code) {

        return new AlarmActivateCommand(smartHome, code);
    }

    @Bean
    @Autowired
    public Command activateAlertCommand(SmartHome smartHome, CommandSender commandSender) {
        return new AlertActivateCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public CloseHallDoorCommand closeHallDoorCommand(SmartHome smartHome, CommandSender commandSender) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    @Autowired
    public Command turnOffAllLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        return new AllLightsOffCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public Command turnOnAllLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        return new AllLightsOnCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public Command turnOnHallLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        return new HallLightsOnCommand(smartHome);
    }

    @Bean
    @Autowired
    public Map<String, Command> myRcButtonsMapping(
            Command activateAlarmCommand,
            Command activateAlertModeCommand,
            Command closeHallDoorCommand,
            Command turnOffAllLightsCommand,
            Command turnOnAllLightsCommand,
            Command turnOnHallLightsCommand
    ) {
        return new HashMap<String, Command>() {{
            put("A", activateAlarmCommand);
            put("B", activateAlertModeCommand);
            put("C", closeHallDoorCommand);
            put("D", turnOffAllLightsCommand);
            put("1", turnOnAllLightsCommand);
            put("2", turnOnHallLightsCommand);
        }};

    }

    @Bean
    RemoteControlImpl remoteControlImplementation(HashMap<String, Command> commandMap) {
        return new RemoteControlImpl(commandMap,"1");
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(List<RemoteControlImpl> remoteControlImplementationList) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlImplementationList.forEach(remoteControlImplementation -> {
            remoteControlRegistry.registerRemoteControl(remoteControlImplementation,remoteControlImplementation.getRcId());
        });
        return remoteControlRegistry;
    }
}
