package org.saxing.chain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Aa001515ChainApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001515ChainApplication.class, args);
//    }

    public static void main(String[] args) {
        OrcKing orcKing = new OrcKing();
        orcKing.makeRequest(new Request(RequestType.COLLECT_TAX, "COLLECT_TAX"));
        orcKing.makeRequest(new Request(RequestType.DEFEND_CASTLE, "DEFEND_CASTLE"));
        orcKing.makeRequest(new Request(RequestType.TORTURE_PRISONER, "TORTURE_PRISONER"));

    }

}
