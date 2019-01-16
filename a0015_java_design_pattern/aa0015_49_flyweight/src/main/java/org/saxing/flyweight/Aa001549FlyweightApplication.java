package org.saxing.flyweight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/1/16 13:39
 */
public class Aa001549FlyweightApplication {

    public static void main(String[] args) {
        AlchemistShop alchemistShop = new AlchemistShop();
        alchemistShop.enumerate();
    }

}

