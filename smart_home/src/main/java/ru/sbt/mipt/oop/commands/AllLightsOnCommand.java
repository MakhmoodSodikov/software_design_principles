package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.Light;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.types.CommandType;

public class AllLightsOnCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public AllLightsOnCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light light = (Light) lightCandidate;

            light.setState(true);

            SensorCommand sensorCommand = new SensorCommand(CommandType.LIGHT_ON, light.getId());
            commandSender.sendCommand(sensorCommand);
        });
    }
}