package org.saxing.eventdrivenarchitecture.handler;

import org.saxing.eventdrivenarchitecture.event.UserCreatedEvent;
import org.saxing.eventdrivenarchitecture.framework.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * user create event
 *
 * @author saxing 2018/12/27 22:31
 */
public class UserCreatedEventHandler implements Handler<UserCreatedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreatedEventHandler.class);

    @Override
    public void onEvent(UserCreatedEvent event) {
        LOGGER.info("User '{}' has been Created!", event.getUser().getName());
    }

}
