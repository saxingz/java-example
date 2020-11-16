package org.saxing;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * bean info
 *
 * @author saxing 2020/11/16 22:24
 *
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);

    }

}
