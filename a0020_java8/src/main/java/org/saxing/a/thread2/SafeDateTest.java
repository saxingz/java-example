package org.saxing.a.thread2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SafeDateTest {

    static class SafeDateFormat {
        // 定义 ThreadLocal 变量
        static final ThreadLocal<DateFormat>
                tl=ThreadLocal.withInitial(
                ()-> new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss"));

        static DateFormat get(){
            return tl.get();
        }
    }
    // 不同线程执行下面代码
// 返回的 df 是不同的
    DateFormat df =
            SafeDateFormat.get();


}
