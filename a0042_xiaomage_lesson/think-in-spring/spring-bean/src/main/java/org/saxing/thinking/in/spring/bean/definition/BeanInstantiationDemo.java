package org.saxing.thinking.in.spring.bean.definition;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanInstantiationDemo
 *
 * @author saxing 2020/11/19 22:39
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);

        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByInstanceMethod == user);
    }

}
