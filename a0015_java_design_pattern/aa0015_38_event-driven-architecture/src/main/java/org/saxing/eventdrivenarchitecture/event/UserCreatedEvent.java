package org.saxing.eventdrivenarchitecture.event;

import org.saxing.eventdrivenarchitecture.model.User;

/**
 * user create event
 *
 * @author saxing 2018/12/27 22:30
 */
public class UserCreatedEvent extends AbstractEvent {

    private User user;

    public UserCreatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
