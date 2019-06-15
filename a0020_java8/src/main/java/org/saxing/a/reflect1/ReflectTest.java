package org.saxing.a.reflect1;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        // method 1
//        Class<?> clazz = Class.forName("org.saxing.a.reflect.User");
//        User user = (User) clazz.newInstance();


        // method 2
//        User user = User.class.newInstance();

        // method 3
        User user2 = new User();
        User user = user2.getClass().newInstance();



        user.setId(11222);
        user.setUserName("小浩");
        user.setPassword("123456");
        System.out.println(user);


        //	Field field=user2.getClass().getDeclaredField("number");
        //	Field field=User.class.getDeclaredField("number");
        Field field=Class.forName("org.saxing.a.reflect1.User").getDeclaredField("id");
        field.setAccessible(true);
        field.set(user2, 3333);
        System.out.println("user2对象的值为:"+user2);
        Method method=User.class.getDeclaredMethod("setUserName",String.class);
        method.invoke(user2,"小浩爷爷");
        System.out.println("user2对象的值为:"+user2);
        Class<?> component=Class.forName("org.saxing.a.reflect1.User").getDeclaredField("password").get(user2).getClass().getComponentType();
        User.class.getDeclaredField("password").setAccessible(true);
        int length=((String[])User.class.getDeclaredField("password").get(user2)).length;
        System.out.println("user2中原始的数组的长度为:"+length);
        Object [] array=(Object[]) Array.newInstance(component, length+75);
        System.out.println("user2中修改后的数组的长度为:"+array.length);
        user.setId(10);
        user.setUserName("小浩");
        user.setPassword("123456");
        System.out.println(user);
    }

}
