package org.saxing.monostate;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/4/26 13:31
 */
public class Aa001566MonostateApplication {

    public static void main(String[] args) {
        LoadBalancer loadBalancer1 = new LoadBalancer();
        LoadBalancer loadBalancer2 = new LoadBalancer();
        loadBalancer1.serverRequest(new Request("Hello"));
        loadBalancer2.serverRequest(new Request("Hello World"));
    }

}
