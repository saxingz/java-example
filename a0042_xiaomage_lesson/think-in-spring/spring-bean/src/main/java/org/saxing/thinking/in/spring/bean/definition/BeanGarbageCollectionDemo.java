package org.saxing.thinking.in.spring.bean.definition;

import org.saxing.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanGarbageCollectionDemo
 *
 * @author saxing 2020/11/21 23:20
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();


        applicationContext.close();
        Thread.sleep(300L);
        System.gc();
        Thread.sleep(300L);
    }

}
