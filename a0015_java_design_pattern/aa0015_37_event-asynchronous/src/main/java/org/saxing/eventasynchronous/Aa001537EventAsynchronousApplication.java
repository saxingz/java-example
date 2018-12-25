package org.saxing.eventasynchronous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * main
 *
 * @author saxing 2018/12/25 22:23
 */
public class Aa001537EventAsynchronousApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001537EventAsynchronousApplication.class);

    public static final String PROP_FILE_NAME = "application.properties";

    boolean interactiveMode = false;

    public static void main(String[] args) {

    }

    /**
     * setup
     */
    public void setUp(){
        Properties prop = new Properties();
        InputStream inputStream = Aa001537EventAsynchronousApplication.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);

        if (inputStream != null){
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                LOGGER.error("{} was not found. Defaulting to non-interactive mode.", PROP_FILE_NAME, e);
            }
            String property = prop.getProperty("INTERACTIVE_MODE");
            if (property.equalsIgnoreCase("YES")){
                interactiveMode = true;
            }
        }
    }

    public void run(){
        if (interactiveMode){
            runInteractiveMode();
        }else{
            quickRun();
        }
    }

    public void quickRun() {

    }

    public void runInteractiveMode() {

    }


}

