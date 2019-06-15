package org.saxing.a.reflect2;

import java.lang.reflect.Method;

public class ReflectTools {

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

}
