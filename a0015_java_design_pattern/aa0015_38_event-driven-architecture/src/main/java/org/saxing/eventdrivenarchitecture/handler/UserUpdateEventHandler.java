package org.saxing.eventdrivenarchitecture.handler;

import org.saxing.eventdrivenarchitecture.event.UserUpdatedEvent;
import org.saxing.eventdrivenarchitecture.framework.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * user update
 *
 * @author saxing 2018/12/27 22:33
 */
public class UserUpdateEventHandler implements Handler<UserUpdatedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdateEventHandler.class);

    @Override
    public void onEvent(UserUpdatedEvent event) {
        LOGGER.info("User '{}' has been Updated!", event.getUser().getName());
    }
}
