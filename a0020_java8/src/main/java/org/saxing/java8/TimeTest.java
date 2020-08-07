package org.saxing.java8;

import java.util.Date;

public class TimeTest {

    public static void main(String[] args) {

        Long current = System.currentTimeMillis();
        Long dateTime = new Date().getTime();

        System.out.println(current);
        System.out.println(dateTime);

        // 1592536526
        // 1596777250822

    }

}
