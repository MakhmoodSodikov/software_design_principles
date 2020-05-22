package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.types.CommandType;

public class AlertActivateCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public AlertActivateCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().warning();
        SensorCommand sensorCommand = new SensorCommand(CommandType.ALERT_MODE_ON, "");
        commandSender.sendCommand(sensorCommand);
    }
}