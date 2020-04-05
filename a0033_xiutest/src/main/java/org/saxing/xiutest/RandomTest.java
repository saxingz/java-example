package org.saxing.xiutest;


import org.apache.commons.lang3.RandomUtils;

public class RandomTest {

    public static void main(String[] args) {

        int num0 = 0;
        int num1 = 0;

        for (int i = 0; i < 1000; i++) {
            long num = RandomUtils.nextLong(0, 2);
            System.out.println(num);
            if (num == 0){
                num0++;
            }
            if (num == 1){
                num1++;
            }
        }

        System.out.println("num0: " + num0);
        System.out.println("num1: " + num1);
    }

}
