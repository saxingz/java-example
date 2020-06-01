package org.saxing.java8;

import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Set;

public class Md5Test {

    public static void main(String[] args) {

        Set<String> result = new HashSet<>();
        Long sample = 0L;


        for (int i = 0; i < 100000; i++) {
            String md5 = DigestUtils.md5DigestAsHex(String.valueOf(sample++).getBytes()).substring(0, 5);
            result.add(md5);
            System.out.println(result.size());
        }
        System.out.println(result.size());
    }

}
