package org.saxing.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * NoUniqueBeanDefinitionExceptionDemo
 *
 * @author saxing 2020/11/22 16:58
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.out.printf("Spring 应用上下文存在%d个 %s 类型的Bean, 具体原因： %s \n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }

        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }

}
