package org.saxing.thinking.in.spring.ioc.overview.repository;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;

import java.util.Collection;

/**
 * UserRepository
 *
 * @author saxing 2020/11/18 22:03
 */
public class UserRepository {

    private Collection<User> users;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
