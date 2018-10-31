package org.saxing.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//@SpringBootApplication
public class Aa001509BalkingApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001509BalkingApplication.class, args);
//    }
    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001509BalkingApplication.class);

    public static void main(String[] args) {
        final Washingmachine washingmachine = new Washingmachine();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(washingmachine::wash);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("ERROR: Waiting on executor service shutdown!");
        }
    }
}
