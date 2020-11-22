package org.saxing.thinking.in.spring.ioc.dependency.injection;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;

/**
 * UserHolder
 *
 * @author saxing 2020/11/22 20:52
 */
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
