package ru.sbt.mipt.oop.interior.alarm.states;

import ru.sbt.mipt.oop.interior.alarm.Alarm;

public class ActivatedAlarmState implements AlarmState {
    private final Alarm alarm;

    public ActivatedAlarmState(Alarm alarm) {
            this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        //already activated
        alarm.setState(new WarningModeAlarmState(alarm));
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new UnactivatedAlarmState(alarm));
        } else {
            warning();
        }
    }

    @Override
    public void warning() {
        alarm.setState(new WarningModeAlarmState(alarm));
    }
}
