package ru.sbt.mipt.oop.interior.alarm.states;

import ru.sbt.mipt.oop.interior.alarm.Alarm;

public class UnactivatedAlarmState implements AlarmState{
    private final Alarm alarm;

    public UnactivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.setState(new ActivatedAlarmState(alarm));
    }

    @Override
    public void deactivate(String code) {
        //already deactivated
    }

    @Override
    public void warning() {
        //can not set Alert mode from this state
    }

}
