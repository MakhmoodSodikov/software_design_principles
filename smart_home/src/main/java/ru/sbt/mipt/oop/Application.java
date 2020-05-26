package ru.sbt.mipt.oop;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import smarthome.SensorEventHandler;
import ru.sbt.mipt.oop.spingconfigs.SpringConfig;

public class Application {

    public static void main(String... args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        applicationContext.getBean(SensorEventHandler.class).start();
    }
}
