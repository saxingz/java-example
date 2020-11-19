package org.saxing.thinking.in.spring.ioc.overview.container;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * AnnotationApplicationContextAsIoCContainer
 *
 * @author saxing 2020/11/18 22:41
 */
public class AnnotationApplicationContextAsIoCContainer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIoCContainer.class);
        applicationContext.refresh();
        lookupCollectionByType(applicationContext);

        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("saxing");
        return user;
    }


    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象： " + users);
        }
    }

}
