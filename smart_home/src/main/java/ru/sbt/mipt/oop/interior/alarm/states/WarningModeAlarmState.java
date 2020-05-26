package ru.sbt.mipt.oop.interior.alarm.states;

import ru.sbt.mipt.oop.interior.alarm.Alarm;

public class WarningModeAlarmState implements AlarmState {
    private final Alarm alarm;

    public WarningModeAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        //already activated
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new UnactivatedAlarmState(alarm));
        }
    }

    @Override
    public void warning() {
        //already on alert mode
    }
}
