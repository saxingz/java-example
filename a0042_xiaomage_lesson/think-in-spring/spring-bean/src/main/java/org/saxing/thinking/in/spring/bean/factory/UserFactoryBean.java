package org.saxing.thinking.in.spring.bean.factory;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * UserFactoryBean
 *
 * @author saxing 2020/11/19 22:59
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
