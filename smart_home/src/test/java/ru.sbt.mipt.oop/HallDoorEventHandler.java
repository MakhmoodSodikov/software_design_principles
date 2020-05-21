package ru.sbt.mipt.oop.eventhandler;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.type.SensorEventType;

public class HallDoorEventHandlerTest {
    private SmartHome smartHome;
    private HallDoorEventHandler hallDoorEventHandler;

    @Before
    public void setup() {
        Room testRoom = new Room(
                Arrays.asList(
                        new Light("1", true),
                        new Light("2", false)),
                Arrays.asList(
                        new Door("1", false),
                        new Door("2", false)),
                "tesRoom");

        Room hall = new Room(
                Arrays.asList(
                        new Light("3", true),
                        new Light("4", true),
                        new Light("5", false),
                        new Light("6", true)),
                Arrays.asList(
                        new Door("3", true)),
                "hall");

        smartHome = new SmartHome(Arrays.asList(testRoom, hall));
        hallDoorEventHandler = new HallDoorEventHandler();
    }

    @Test
    public void handleCloseHallDoorEventTest() {
        final String hallDoorId = "3";

        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);
        hallDoorEventHandler.handleEvent(smartHome, sensorEvent);

        TestUtils.getAllLightsCopy(smartHome).forEach(light -> Assert.assertFalse(light.isOn()));
    }

    @Test
    public void handleNotHallDoorEventTest() {
        List<Light> initialAllLightsCopy = TestUtils.getAllLightsCopy(smartHome);

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        hallDoorEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllLightsCopy, TestUtils.getAllLightsCopy(smartHome));
    }

}