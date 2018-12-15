package org.saxing.doublecheckedblocked;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//@SpringBootApplication
public class Aa001529DoubleCheckedBlockedApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001529DoubleCheckedBlockedApplication.class);

    public static void main(String[] args) {
        final Inventory inventory = new Inventory(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                while (inventory.addItem(new Item())) {};
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            LOGGER.error("Error waiting for ExecutorService shutdown");
        }
    }

}

