package org.saxing.pdf;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        String str = "abcdef";
        String[] split = str.split("");
        String result = "";
        for (String s : split){
            result += randomChar();
            result += s;
        }
        System.out.println(split);

        System.out.println(result);


    }

    private static String randomChar(){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return str.split("")[new Random().nextInt(str.length())];
    }

}
