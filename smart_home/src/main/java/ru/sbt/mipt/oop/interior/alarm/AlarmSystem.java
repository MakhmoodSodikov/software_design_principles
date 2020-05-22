package ru.sbt.mipt.oop.interior.alarm;

import ru.sbt.mipt.oop.interior.alarm.states.AlarmState;

public interface AlarmSystem {
    void activate(String code);

    void deactivate(String code);

    void alarm();

    AlarmState getState();
}