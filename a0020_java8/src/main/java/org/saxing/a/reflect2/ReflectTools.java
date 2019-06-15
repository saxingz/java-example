package org.saxing.a.reflect2;

import java.lang.reflect.Method;

public class ReflectTools {

    /**
     *
     * @param obj: 方法执行的那个对象.
     * @param methodName: 类的一个方法的方法名. 该方法也可能是私有方法.
     * @param args: 调用该方法需要传入的参数
     * @return: 调用方法后的返回值
     *
     */
    public Object invoke(Object obj, String methodName, Object ... args) throws Exception{
        //1. 获取 Method 对象
        //   因为getMethod的参数为Class列表类型，所以要把参数args转化为对应的Class类型。

        Class [] parameterTypes = new Class[args.length];
        for(int i = 0; i < args.length; i++){
            parameterTypes[i] = args[i].getClass();
            System.out.println(parameterTypes[i]);
        }

        Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
        //如果使用getDeclaredMethod，就不能获取父类方法，如果使用getMethod，就不能获取私有方法

        //
        //2. 执行 Method 方法
        //3. 返回方法的返回值
        return method.invoke(obj, args);
    }

    /**
     * @param className: 某个类的全类名
     * @param methodName: 类的一个方法的方法名. 该方法也可能是私有方法.
     * @param args: 调用该方法需要传入的参数
     * @return: 调用方法后的返回值
     */
    public Object invoke(String className, String methodName, Object ... args){
        Object obj = null;

        try {
            obj = Class.forName(className).newInstance();
            //调用上一个方法
            return invoke(obj, methodName, args);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
