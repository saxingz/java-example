package org.saxing.reactor;

import java.util.Random;

/**
 * random int test
 *
 * @author saxing 2020/8/18 22:53
 */
public class RandomInt {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(899999) + 100000);
        }
    }

}
