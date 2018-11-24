package org.saxing.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing  2018/11/24 13:12
 */
//@SpringBootApplication
public class Aa001518CompositeApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001518CompositeApplication.class, args);
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001518CompositeApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Message from the orcs: ");

        LetterComposite orcMessage = new Messenger().messageFromOrcs();
        orcMessage.print();

        LOGGER.info("\nMessage from the elves: ");

        LetterComposite elfMessage = new Messenger().messageFromElves();
        elfMessage.print();
    }

}
