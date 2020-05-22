package ru.sbt.mipt.oop.commands;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.Light;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.types.CommandType;

public class AllLightsOffCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public AllLightsOffCommand(SmartHome smartHome, CommandSender commandSender) {
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

            light.setState(false);

            SensorCommand sensorCommand = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(sensorCommand);
        });
    }
}
