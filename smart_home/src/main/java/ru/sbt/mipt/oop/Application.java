package ru.sbt.mipt.oop;
import ru.sbt.mipt.oop.eventhandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.LightEventHandler;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.HomeJsonDataLoader;
import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeJsonDataLoader HomeInstance = new HomeJsonDataLoader("smart-home-1.json");
        SmartHome smartHome = HomeInstance.loadData();

        // начинаем цикл обработки событий
        EventsExecutor eventsExec = new EventsExecutor(smartHome, Arrays.asList(
                new LightEventHandler(),
                new DoorEventHandler(),
                new HallDoorEventHandler()
        ));

        eventsExec.startLoop();
    }
}
