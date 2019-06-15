package org.saxing.a.reflect2;

import java.lang.reflect.Field;

public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = null;

        clazz = Person.class;

        Field[] fields = clazz.getDeclaredFields();


        //1.通过类名
        clazz = Person.class;

        //2.通过对象名
        //这种方式是用在传进来一个对象，却不知道对象类型的时候使用
        Person person = new Person();
        clazz = person.getClass();
        //上面这个例子的意义不大，因为已经知道person类型是Person类，再这样写就没有必要了
        //如果传进来是一个Object类，这种做法就是应该的
        Object obj = new Person();
        clazz = obj.getClass();


        //3.通过全类名(会抛出异常)
        //一般框架开发中这种用的比较多，因为配置文件中一般配的都是全类名，通过这种方式可以得到Class实例
        String className="org.saxing.a.reflect2.Person";
        clazz = Class.forName(className);

        //字符串的例子
        clazz = String.class;

        clazz = "javaTest".getClass();

        clazz = Class.forName("java.lang.String");



        System.out.println();
    }

}
