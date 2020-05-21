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

public class DoorEventHandlerTest {
    private SmartHome smartHome;
    private DoorEventHandler doorEventHandler;

    @Before
    public void setup() {
        Room testRoom = new Room(
                Arrays.asList(
                        new Light("1", false),
                        new Light("2", true)),
                Arrays.asList(
                        new Door("1", false),
                        new Door("2", false),
                        new Door("3", false),
                        new Door("4", false)),
                "tesRoom");

        smartHome = new SmartHome(Arrays.asList(testRoom));
        doorEventHandler = new DoorEventHandler();
    }

    @Test
    public void openExistingDoorEventTest() {
        final String doorId = "1";
        TestUtils.getDoorById(smartHome, doorId).close();
        List<Door> initialAllDoorsCopy = TestUtils.getAllDoorsExceptOneCopy(smartHome, doorId);

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);
        doorEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllDoorsCopy, TestUtils.getAllDoorsExceptOneCopy(smartHome, doorId));
        Assert.assertTrue(TestUtils.getDoorById(smartHome, doorId).isOpen());
    }

    @Test
    public void closeExistingDoorEventTest() {
        final String doorId = "1";
        List<Door> initialAllDoorsCopy = TestUtils.getAllDoorsExceptOneCopy(smartHome, doorId);
        TestUtils.getDoorById(smartHome, doorId).open();

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
        doorEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllDoorsCopy, TestUtils.getAllDoorsExceptOneCopy(smartHome, doorId));
        Assert.assertFalse(TestUtils.getDoorById(smartHome, doorId).isOpen());
    }

    @Test
    public void openNonExistingDoorEventTest() {
        final String doorId = "100";
        List<Door> initialAllDoorsCopy = TestUtils.getAllDoorsCopy(smartHome);

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);
        doorEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllDoorsCopy, TestUtils.getAllDoorsCopy(smartHome));
    }

    @Test
    public void closeNonExistingDoorEventTest() {
        final String doorId = "100";
        List<Door> initialAllDoorsCopy = TestUtils.getAllDoorsCopy(smartHome);

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
        doorEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllDoorsCopy, TestUtils.getAllDoorsCopy(smartHome));
    }

    @Test
    public void handleNotDoorEventTest() {
        List<Door> initialAllDoorsCopy = TestUtils.getAllDoorsCopy(smartHome);

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        doorEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllDoorsCopy, TestUtils.getAllDoorsCopy(smartHome));
    }

}