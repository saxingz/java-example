package org.saxing.java8;

import java.lang.management.ManagementFactory;
import java.util.Random;

public class LongTest {

    public static void main(String[] args) {
        test2();

    }

    public static void test2(){
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int workId = new Random().nextInt(1023);
//            if (workId >= 1022 ){
//                System.out.println(workId);
//            }
            if (workId <= 1){
                System.out.println(workId);
            }
        }

    }

    public static void test1(){
        //        long maxValue = Long.MAX_VALUE;
//        // 9223372036854775807
//        System.out.println(maxValue);


        // 在windows上，获取到得name格式为 1234@userName
        // 1234为PID，@为分隔符，userName为当前用户
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        int indexOf = pid.indexOf('@');
        if (indexOf > 0) {
            pid = pid.substring(0, indexOf);
        }

        System.out.println("当前JVM Process ID: " + pid);
    }
}
