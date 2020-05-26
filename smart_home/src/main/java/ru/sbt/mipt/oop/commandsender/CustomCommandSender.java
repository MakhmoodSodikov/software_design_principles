package ru.sbt.mipt.oop.commandsender;

import ru.sbt.mipt.oop.sensor.SensorCommand;

public class CustomCommandSender implements CommandSender {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
