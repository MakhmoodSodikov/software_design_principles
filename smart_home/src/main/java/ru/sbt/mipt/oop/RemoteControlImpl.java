package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.smarthome.remote.RemoteControl;

import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private final Map<String, Command> rcButtonToCommandMap;
    private final String rcId;

//    TODO!!!
    public RemoteControlImpl(Map<String, Command> rcButtonToCommandMap, String rcId) {
        this.rcButtonToCommandMap = rcButtonToCommandMap;
        this.rcId = rcId;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (!rcId.equals(this.rcId)) {
            return;
        }

        if (rcButtonToCommandMap.containsKey(buttonCode)) {
            rcButtonToCommandMap.get(buttonCode).execute();
        }
    }

    public String getRcId() {
        return rcId;
    }
}
