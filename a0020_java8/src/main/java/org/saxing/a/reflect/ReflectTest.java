package org.saxing.a.reflect;

public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // method 1
        Class<?> clazz = Class.forName("org.saxing.a.reflect.User");
        User user = (User) clazz.newInstance();


        // method 2
//        User user = User.class.newInstance();


        user.setId(10);
        user.setUserName("小浩");
        user.setPassword("123456");
        System.out.println(user);
    }

}
