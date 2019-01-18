package org.saxing.frontcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * main
 *
 * @author saxing 2019/1/17 22:57
 */
public class Aa001550FrontControllerApplication {

    public static void main(String[] args) {
//        FrontController controller = new FrontController();
//        controller.handleRequest("Archer");
//        controller.handleRequest("Catapult");
//        controller.handleRequest("foobar");

        Byte a = 1;
        Byte b = 1;
        List list = Arrays.asList(a, b);
        System.out.println(list);
        List xxx =  list;
        System.out.println(xxx);
    }

}

