package org.saxing.a.java8;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) {
        Date date2=new Date();
        date2.setTime(2199023255552L);
        System.err.println(date2);

        LocalDateTime d1 = LocalDateTime.of(2038, 12, 1, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2039, 12, 1, 0, 0);
        LocalDateTime d3 = LocalDateTime.of(2040, 12, 1, 0, 0);

        System.out.println(d1);
        System.out.println(d1.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(Long.toBinaryString(d1.toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        System.out.println(d2);
        System.out.println(d2.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(Long.toBinaryString(d2.toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        System.out.println(d3);
        System.out.println(d3.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(Long.toBinaryString(d3.toInstant(ZoneOffset.of("+8")).toEpochMilli()));
    }

}
