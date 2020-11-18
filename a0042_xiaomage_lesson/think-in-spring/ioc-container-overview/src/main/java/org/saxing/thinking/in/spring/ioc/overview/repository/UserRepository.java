package org.saxing.thinking.in.spring.ioc.overview.repository;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;

import java.util.Collection;

/**
 * UserRepository
 *
 * @author saxing 2020/11/18 22:03
 */
public class UserRepository {

    private Collection<User> users;

    private BeanFactory beanFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
