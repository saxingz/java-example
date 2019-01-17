package org.saxing.frontcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/1/17 22:57
 */
public class Aa001550FrontControllerApplication {

    public static void main(String[] args) {
        FrontController controller = new FrontController();
        controller.handleRequest("Archer");
        controller.handleRequest("Catapult");
        controller.handleRequest("foobar");
    }

}

