package org.saxing.thinking.in.spring.ioc.overview.container;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * IoCContainer
 *
 * @author saxing 2020/11/18 22:41
 */
public class BeanfactoryAsIoCContainer {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("bean account: " + beanDefinitionCount);

        lookupCollectionByType(beanFactory);
    }


    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象： " + users);
        }
    }

}
