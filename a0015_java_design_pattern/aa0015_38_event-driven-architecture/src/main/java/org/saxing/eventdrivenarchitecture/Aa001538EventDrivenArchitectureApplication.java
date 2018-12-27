package org.saxing.eventdrivenarchitecture;

import org.saxing.eventdrivenarchitecture.event.UserCreatedEvent;
import org.saxing.eventdrivenarchitecture.event.UserUpdatedEvent;
import org.saxing.eventdrivenarchitecture.framework.EventDispatcher;
import org.saxing.eventdrivenarchitecture.handler.UserCreatedEventHandler;
import org.saxing.eventdrivenarchitecture.handler.UserUpdateEventHandler;
import org.saxing.eventdrivenarchitecture.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2018/12/27 22:34
 */
public class Aa001538EventDrivenArchitectureApplication {

    public static void main(String[] args) {

        EventDispatcher eventDispatcher = new EventDispatcher();
        eventDispatcher.registerHandler(UserCreatedEvent.class, new UserCreatedEventHandler());
        eventDispatcher.registerHandler(UserUpdatedEvent.class, new UserUpdateEventHandler());

        User user = new User("saxing");
        eventDispatcher.dispatch(new UserCreatedEvent(user));
        eventDispatcher.dispatch(new UserUpdatedEvent(user));

    }

}

