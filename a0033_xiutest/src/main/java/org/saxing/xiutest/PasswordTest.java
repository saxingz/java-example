package org.saxing.xiutest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordTest {

    public static void main(String[] args) {
        boolean res = checkPassword("11111w11r");
        System.out.println(res);
    }

    public static boolean checkPassword(String password){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,20})$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


}
