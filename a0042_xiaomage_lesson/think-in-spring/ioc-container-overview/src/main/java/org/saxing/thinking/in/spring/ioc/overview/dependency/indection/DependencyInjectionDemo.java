package org.saxing.thinking.in.spring.ioc.overview.dependency.indection;

import org.saxing.thinking.in.spring.ioc.overview.annotation.Super;
import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.saxing.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 *  injection
 *
 * @author saxing 2020/11/17 23:03
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

        System.out.println(userRepository.getUsers());

    }



}
