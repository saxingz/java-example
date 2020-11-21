package org.saxing.thinking.in.spring.bean.definition;

import org.saxing.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.saxing.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * SingletonBeanRegistrationDemo
 *
 * @author saxing 2020/11/21 23:25
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 外部对象注入托管
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();
        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);
        System.out.println("userfactory == userfactoryByLookup ? : " + (userFactory == userFactoryByLookup));


        applicationContext.close();

    }

    public static void main2(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 外部对象注入托管
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();
        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println("userfactory == userfactoryByLookup ? :" + (userFactory == userFactoryByLookup));


        applicationContext.close();

    }

}
