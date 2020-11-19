package org.saxing.thinking.in.spring.bean.definition;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanAliasDemo
 *
 * @author saxing 2020/11/19 20:55
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        User saxingUser = beanFactory.getBean("saxing-user", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("是否相同： " + (user == saxingUser));

    }

}
