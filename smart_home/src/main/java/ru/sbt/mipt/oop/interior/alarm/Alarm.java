package ru.sbt.mipt.oop.interior.alarm;

import ru.sbt.mipt.oop.interior.alarm.states.AlarmState;
import ru.sbt.mipt.oop.interior.alarm.states.UnactivatedAlarmState;
import ru.sbt.mipt.oop.interior.alarm.states.WarningModeAlarmState;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm() {
        this.state = new UnactivatedAlarmState(this);
        this.code = "";
    }

    public boolean isActivated() {
        return !(state instanceof UnactivatedAlarmState);
    }

    public boolean isOnWarning() {
        return state instanceof WarningModeAlarmState;
    }

    private void setState(AlarmState state) {
        this.state = state;
    }

    private String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void warning() {
        state.warning();
    }

    public AlarmState getState() {
        return state;
    }
}
