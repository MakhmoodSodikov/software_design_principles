package ru.sbt.mipt.oop;
import ru.sbt.mipt.oop.eventhandlers.*;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.HomeJsonDataLoader;
import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeJsonDataLoader HomeInstance = new HomeJsonDataLoader("smart-home-1.json");
        SmartHome smartHome = HomeInstance.loadData();

        List<EventHandler> handlers = Arrays.asList(new AlarmEventHandler(),
                new SmsDecorator(Arrays.asList(new DoorEventHandler(),
                        new LightEventHandler(),
                        new HallDoorEventHandler())));

        // начинаем цикл обработки событий
        EventsExecutor eventsExec = new EventsExecutor(smartHome, handlers, new CustomEventGenerator());

        eventsExec.startLoop();
    }
}
