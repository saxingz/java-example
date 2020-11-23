package org.saxing.thinking.in.spring.ioc.dependency.injection;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * AnnotationDependencyConstructorInjectionDemo
 *
 * @author saxing 2020/11/22 20:49
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired // 会忽略静态字段
    private UserHolder userHolder;
    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        UserHolder userHolder = demo.userHolder;
        UserHolder userHolder2 = demo.userHolder;
        System.out.println(userHolder);
        System.out.println(userHolder2);
        System.out.println(userHolder == userHolder2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
