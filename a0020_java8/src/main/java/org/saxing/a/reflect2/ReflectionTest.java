package org.saxing.a.reflect2;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        new ReflectionTest().testMethod5();
    }

    public void testMethod5() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName("org.saxing.a.reflect2.Person");

        //
        //1.获取方法
        //  1.1 获取取clazz对应类中的所有方法--方法数组（一）
        //     不能获取private方法,且获取从父类继承来的所有方法
        Method[] methods = clazz.getMethods();
        for(Method method:methods){
            System.out.print(" "+method.getName());
        }
        System.out.println();

        //
        //  1.2.获取所有方法，包括私有方法 --方法数组（二）
        //  所有声明的方法，都可以获取到，且只获取当前类的方法
        methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            System.out.print(" "+method.getName());
        }
        System.out.println();

        //
        //  1.3.获取指定的方法
        //  需要参数名称和参数列表，无参则不需要写
        //  对于方法public void setName(String name) {  }
        Method method = clazz.getDeclaredMethod("setName", String.class);
        System.out.println(method);
        //  而对于方法public void setAge(int age) {  }
        method = clazz.getDeclaredMethod("setAge", int.class);
        System.out.println(method);
        //  这样写是获取不到的，如果方法的参数类型是int型
        //  如果方法用于反射，那么要么int类型写成Integer： public void setAge(Integer age) {  }
        //  要么获取方法的参数写成int.class


        //2.执行方法
        //  invoke第一个参数表示执行哪个对象的方法，剩下的参数是执行方法时需要传入的参数
        Object obje = clazz.newInstance();
        method.invoke(obje,2);

        //如果一个方法是私有方法，第三步是可以获取到的，但是这一步却不能执行
        //私有方法的执行，必须在调用invoke之前加上一句method.setAccessible（true）;
    }

    public void testClassLoader4(){
        //src目录下，直接加载
        InputStream in1 = null;
        in1 = this.getClass().getClassLoader().getResourceAsStream("test1.txt");

        //放在内部文件夹，要写全路径
        InputStream in2 = null;
        in2 = this.getClass().getClassLoader().getResourceAsStream("org/saxing/a/reflect2/test2.txt");
    }


    public void testClassLoader3() throws ClassNotFoundException {
        //1. 获取一个系统的类加载器(可以获取，当前这个类PeflectTest就是它加载的)
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        //2. 获取系统类加载器的父类加载器（扩展类加载器，可以获取）.
        classLoader = classLoader.getParent();
        System.out.println(classLoader);

        //3. 获取扩展类加载器的父类加载器（引导类加载器，不可获取）.
        classLoader = classLoader.getParent();
        System.out.println(classLoader);

        //4. 测试当前类由哪个类加载器进行加载（系统类加载器）:
        classLoader = Class.forName("org.saxing.a.reflect2.ReflectionTest")
                .getClassLoader();
        System.out.println(classLoader);

        //5. 测试 JDK 提供的 Object 类由哪个类加载器负责加载（引导类）
        classLoader = Class.forName("java.lang.Object")
                .getClassLoader();
        System.out.println(classLoader);
    }


    public void testClass2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1.获取Class对象
        String className="org.saxing.a.reflect2.Person";
        Class clazz = Class.forName(className);

        //利用Class对象的newInstance方法创建一个类的实例
        Object obj =  clazz.newInstance();
        System.out.println(obj);
    }

    public void testClass1() throws ClassNotFoundException {
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
