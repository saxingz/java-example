package org.saxing.thinking.in.spring.bean.definition;

import org.saxing.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.saxing.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BeanInitializationDemo
 *
 * @author saxing 2020/11/19 23:20
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();

        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        applicationContext.close();
    }


    @Bean
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
