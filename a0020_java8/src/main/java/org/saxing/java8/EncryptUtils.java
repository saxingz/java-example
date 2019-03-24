package org.saxing.java8;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EncryptUtils {

    public static void main(String[] args) {
        Long entId = 111L;
        String secret = "aaaabbbb";

        String password = getPassword(111L, secret);

        System.out.println("password:" + password);
        System.out.println("uuid:" + get());
        System.out.println("uuid.length:" + get().length());

        System.out.println(verify(entId, secret, password));;
    }

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 加密
     *
     * @param entId
     * @param secret
     * @return
     */
    public static String getPassword(Long entId, String secret){
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);

        String entIdStr = entId + "";
        int length = (entIdStr).length();

        for (int i = 0; i < 10 - length; i++) {
            entIdStr = "0" + entIdStr;
        }
        return encoder.encode(entIdStr + secret + time);
    }

    public static Boolean verify(Long entId, String secret, String password){

        boolean valid = false;
        Set<String> probable = new HashSet<>();
        String entIdStr = entId + "";
        int length = (entIdStr).length();

        for (int i = 0; i < 10 - length; i++) {
            entIdStr = "0" + entIdStr;
        }

        LocalDateTime localDateTimeToPlus = LocalDateTime.now();
        LocalDateTime localDateTimeToMin = LocalDateTime.now();

        for (int i = 0; i < 10; i++) {
            String addTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTimeToPlus);
            probable.add(entIdStr + secret + addTime);

            String minTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTimeToMin);
            probable.add(entIdStr + secret + minTime);

            localDateTimeToPlus = localDateTimeToPlus.plusSeconds(1);
            localDateTimeToMin = localDateTimeToMin.minusSeconds(1);
        }

        for (String pass : probable){
            if (encoder.matches(pass, password)){
                valid = true;
            }
        }

        return valid;
    }

    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
