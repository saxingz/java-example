# java example
java exercises and demo

design pattern

- 返回值有的时候可以用 result包装一下 可以透传 状态码
- 少用byte, 因为 arraylist 泛型问题，如dubbo里的坑
- 方法里最好用包装类，反射时，动态Object[]类型参数传入，解析出来的肯定是Integer。用int不好反解析
