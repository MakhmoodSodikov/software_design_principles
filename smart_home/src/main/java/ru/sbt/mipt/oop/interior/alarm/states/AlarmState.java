package ru.sbt.mipt.oop.interior.alarm.states;

public interface AlarmState {
    void activate(String code);

    void deactivate(String code);

    void warning();
}