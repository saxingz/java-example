package org.saxing.java8;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) {

        Long current = System.currentTimeMillis();
        // 1596789092056
        System.out.println(current);


        Long dateTime = new Date().getTime();
        // 1596789092057
        System.out.println(dateTime);

        // 1592536526
        //
        // 1596777250822


        LocalDateTime localDateTime = LocalDateTime.now();
        long currentSecond = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        LocalDateTime past8Minutes = localDateTime.minusMinutes(8);
        long pastTimeSecond = past8Minutes.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();

        System.out.println(currentSecond);
        System.out.println(pastTimeSecond);
    }

}
