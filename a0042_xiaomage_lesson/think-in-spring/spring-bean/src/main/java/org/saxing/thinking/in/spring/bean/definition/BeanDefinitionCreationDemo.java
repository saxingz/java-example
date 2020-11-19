package org.saxing.thinking.in.spring.bean.definition;

import org.saxing.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * BeanDefinitionCreationDemo
 *
 * @author saxing 2020/11/19 20:30
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "saxing");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();


        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", 1)
                .add("name", "saxing");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }

}
