package org.saxing.thinking.in.spring.ioc.dependency.injection;

import org.saxing.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * QualifierAnnotationDependencyInjectionDemo
 *
 * @author saxing 2020/11/27 23:39
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Bean
    @Qualifier // 进行逻辑分组
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier  // 进行逻辑分组
    public User user2() {
        return createUser(8L);
    }

    @Autowired
    private Collection<User> allUsers;  // 2 beans

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; //2 beans = user1 + user2

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.namedUser = " + demo.namedUser);
        System.out.println("demo.allUsers = " + demo.allUsers);
        System.out.println("demo.qualifiedUsers = " + demo.qualifiedUsers);
        System.out.println("demo.groupedUsers = " + demo.groupedUsers);


        applicationContext.close();
    }

}
