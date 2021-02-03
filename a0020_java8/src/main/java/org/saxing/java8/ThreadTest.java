package org.saxing.java8;

import java.time.LocalDate;
import java.time.Month;

public class ThreadTest {

    public static void main(String[] args) {
        dealSql();
    }

    public static void dealSql() {

        LocalDate date = LocalDate.of(2020, 12, 1);
        for (LocalDate d = date; d.isBefore(LocalDate.now()); d = d.plusDays(1L)) {
            int year = d.getYear();
            int monthValue = d.getMonthValue();
            int dayOfMonth = d.getDayOfMonth();
            String monthStr = String.valueOf(monthValue);
            if (monthValue < 10) {
                monthStr = "0" + monthStr;
            }
            String dayStr = String.valueOf(dayOfMonth);
            if (dayOfMonth < 10) {
                dayStr = "0" + dayStr;
            }
            String sql = "SELECT * from access_log_"+year+ monthStr + dayStr + " WHERE path like '/attendance/v1/admin/mi/atm/%' limit 1000;";
            System.out.println(sql);
        }
    }

    public static void main2(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                long id = Thread.currentThread().getId();
                System.out.println(id);
            }).start();
        }
    }




}
