package org.saxing.ambassador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing  2018/10/14 15:03
 */
//@SpringBootApplication
public class App {

    public static void main(String[] args) {
        Client host1 = new Client();
        Client host2 = new Client();
        host1.useService(12);
        host2.useService(73);
    }
}
