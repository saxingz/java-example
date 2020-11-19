package org.saxing.thinking.in.spring.bean.factory;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;

/**
 * user factory
 *
 * @author saxing 2020/11/19 22:53
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }

}
