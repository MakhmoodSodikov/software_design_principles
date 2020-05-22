package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.interior.alarm.Alarm;

public class AlarmActivateCommand implements Command {
    private SmartHome smartHome;
    private String password;


    public AlarmActivateCommand(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }


    @Override
    public void execute() {
        Alarm alarm = smartHome.getAlarm();
        alarm.activate(password);
    }
}