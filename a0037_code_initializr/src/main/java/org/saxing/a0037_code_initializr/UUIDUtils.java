package org.saxing.a0037_code_initializr;

import java.util.UUID;

public class UUIDUtils {

    /**
     * generate UUID without '-'
     *
     * @return
     */
    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String get(Integer length){
        String uid = get();
        if (length >= uid.length()){
            return uid;
        } else {
            return uid.substring(0, length);
        }
    }

    public static void main(String[] args) {
        System.out.println(get());
        System.out.println(get(30));
        System.out.println(get(31));
        System.out.println(get(32));
        System.out.println(get(33));
        System.out.println(get(34));
        System.out.println(get(35));
    }

}
