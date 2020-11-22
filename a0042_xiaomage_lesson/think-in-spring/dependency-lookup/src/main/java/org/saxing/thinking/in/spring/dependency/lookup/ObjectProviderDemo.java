package org.saxing.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * ObjectProviderDemo
 * 
 * @author saxing 2020/11/22 11:28
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);
        
        applicationContext.close();
    }

    @Bean
    public String helloWorld() { // 默认情况下，方法名为bean名称
        return "Hello, World";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

}
