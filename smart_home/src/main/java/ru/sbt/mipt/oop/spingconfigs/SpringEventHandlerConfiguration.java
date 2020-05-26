package ru.sbt.mipt.oop.spingconfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.commandsender.CommandSenderInterface;
import ru.sbt.mipt.oop.eventhandlers.*;

@Configuration
public class SpringEventHandlerConfiguration {
    @Bean
    CommandSenderInterface CommandSender() {
        return new CommandSender();
    }

    @Bean
    EventHandler alarmEventHandler() {
        return new AlarmEventHandler();
    }

    @Bean
    EventHandler doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    @Autowired
    EventHandler hallDoorEventHandler(CommandSenderInterface commandSender) {
        return new HallDoorEventHandler(commandSender);
    }

    @Bean
    EventHandler lightEventHandler() {
        return new LightEventHandler();
    }
}