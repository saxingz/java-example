package org.saxing.copy4;

import java.io.*;

/**
 * 其实出现问题的关键就在于clone()方法上，我们知道该clone()方法是使用Object类的clone()方法，但是该方法存在一个缺陷，它并不会将对象的所有属性全部拷贝过来，而是有选择性的拷贝，基本规则如下：
 *
 *       1、 基本类型
 *
 *          如果变量是基本很类型，则拷贝其值，比如int、float等。
 *
 *       2、 对象
 *
 *          如果变量是一个实例对象，则拷贝其地址引用，也就是说此时新对象与原来对象是公用该实例变量。
 *
 *       3、 String字符串
 *
 *          若变量为String字符串，则拷贝其地址引用。但是在修改时，它会从字符串池中重新生成一个新的字符串，原有紫都城对象保持不变。
 *
 * 如何利用序列化来完成对象的拷贝呢？在内存中通过字节流的拷贝是比较容易实现的。把母对象写入到一个字节流中，再从字节流中将其读出来，这样就可以创建一个新的对象了，并且该新对象与母对象之间并不存在引用共享的问题，真正实现对象的深拷贝。
 *
 * 使用该工具类的对象必须要实现Serializable接口，否则是没有办法实现克隆的。
 */
public class CloneUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj){
        T cloneObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            //返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

}
