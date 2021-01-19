package org.saxing.age;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AgeTest {

    public static void main(String[] args) throws ParseException {
        String startDateStr = "2016-06-19";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);

        String endDateStr = "2020-06-09";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

        Integer mouthAge = getMouthAge(startDate, endDate);
        System.out.println(mouthAge);
    }

    /**
     * 根据出生日期算出月龄
     */
    public static Integer getMouthAge(Date startDate, Date endDate){
        if(null == startDate){
            return 0;
        }
        Calendar calInfo = GregorianCalendar.getInstance();
        calInfo.setTime(startDate);
        int y1 = calInfo.get(Calendar.YEAR);
        int m1 = calInfo.get(Calendar.MONTH) + 1;
        int d1 = calInfo.get(Calendar.DATE);

        calInfo.setTime(endDate);
        int y2 = calInfo.get(Calendar.YEAR);
        int m2 = calInfo.get(Calendar.MONTH) + 1;
        int d2 = calInfo.get(Calendar.DATE);

        int age = m2 - m1;
        int yy = y2-y1;
        if (d2 < d1) {
            age--;
        }

        if(age < 0){
            age+=12;
            yy --;
        }
        int agestr = 0;
        if(yy > 0){
            agestr += (yy)*12 ;
        }
        agestr += age ;
        return agestr;
    }

}
