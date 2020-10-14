package org.saxing.java8;

import org.apache.commons.lang.StringUtils;

/**
 * string test
 *
 * @author saxing 2020/7/13 22:59
 */
public class StringTest {

    public static void main2(String[] args) {
        String url = "http://resource-zh.7kid.com.cn/////////images/82/teachplan/2019/8/20/2061566286249587.jpg";
        url = url.replaceAll("//", "/");
        url = url.replaceAll("//", "/");
        url = url.replaceAll("//", "/");
        url = url.replaceAll("//", "/");
        url = url.replaceAll("//", "/");
        url = url.replaceAll("//", "/");
        url = url.replaceAll("//", "/");
        url = url.replaceAll("//", "/");
        System.out.println(url);

    }


    public static void main(String[] args) {

        String task = "\uD83D\uDE12\uD83D\uDE12\uD83D\uDE12\uD83D\uDE12\uD83D\uDE12\uD83D\uDE12\uD83D\uDE12\uD83D\uDE1212345";
        long count = task.codePoints().count();
        System.out.println(count);
//        int length = StringUtils.length(task);
//        System.out.println(length);
//        System.out.println(task.length());
//        String taskShort = task.length() > 10
//                ? StringUtils.substring(task, task.offsetByCodePoints(0, 0),
//                task.offsetByCodePoints(0, 10)).concat("...")
//                : task;
//        System.out.println(taskShort);

    }

}
