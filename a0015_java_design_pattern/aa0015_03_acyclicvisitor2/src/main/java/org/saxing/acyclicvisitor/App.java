package org.saxing.acyclicvisitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing  2018/10/8 23:40
 */
//@SpringBootApplication
public class App {


    public static void main(String[] args) {
        ConfigForUnixVisitor conUnix = new ConfigForUnixVisitor();
        ConfigForDosVisitor conDos = new ConfigForDosVisitor();

        Zoom zoom = new Zoom();
        Hayes hayes = new Hayes();

        hayes.accept(conUnix);
        zoom.accept(conUnix);
        hayes.accept(conDos);
        zoom.accept(conDos);
    }
}
