package org.saxing.java8;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DoTest {

    public static void main(String[] args) {
        testCrons();
    }

    public static void testCrons() {
        try {

            CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();

            // 每天早上10：15触发
            cronTriggerImpl.setCronExpression("0/10 * 0-6 * * ?");

            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 2);// 把统计的区间段设置为从现在到1月后的今天（主要是为了方法通用考虑)

            // 这里的时间是根据corn表达式算出来的值
            List<Date> dates = TriggerUtils.computeFireTimesBetween(
                    cronTriggerImpl, null, now,
                    calendar.getTime());
            System.out.println(dates.size());

            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            for (Date date : dates) {
                System.out.println(dateFormat.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) {
        int i = 5;
        System.out.println("fffff");
        i = 10;
    }

    public static void main3(String[] args) {
        List<String> alist = new ArrayList<>();
        List<String> blist = new ArrayList<>();

        alist.add("a");
        alist.add("b");
        alist.add("c");
        alist.add("d");

        blist.add("c");
        blist.add("d");
        blist.add("e");
        blist.add("f");

        boolean b = alist.retainAll(blist);
        System.out.println(b);
        System.out.println(alist);
        System.out.println(blist);
    }

}
