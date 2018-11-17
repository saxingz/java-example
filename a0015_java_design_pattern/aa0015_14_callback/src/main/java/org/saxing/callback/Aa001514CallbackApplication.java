package org.saxing.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 * 
 * @author saxing  2018/11/17 11:41
 */
//@SpringBootApplication
public class Aa001514CallbackApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001514CallbackApplication.class, args);
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001514CallbackApplication.class);

    /**
     * Program entry point
     */
    public static void main(String[] args) {
        Task task = new SimpleTask();
        Callback callback = () -> LOGGER.info("I'm done now.");
        task.executeWith(callback);
    }
}
