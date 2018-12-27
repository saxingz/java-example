package org.saxing.eventdrivenarchitecture.event;

import org.saxing.eventdrivenarchitecture.model.User;

/**
 * user update
 *
 * @author saxing 2018/12/27 22:30
 */
public class UserUpdatedEvent extends AbstractEvent {

    private User user;

    public UserUpdatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
