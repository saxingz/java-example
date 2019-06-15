package org.saxing.a.reflect;

public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("org.saxing.a.reflect.User");
        User user = (User) clazz.newInstance();

    }

}
