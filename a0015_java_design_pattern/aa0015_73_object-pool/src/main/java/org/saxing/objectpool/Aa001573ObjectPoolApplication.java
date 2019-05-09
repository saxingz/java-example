package org.saxing.objectpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/5/9 15:24
 */
public class Aa001573ObjectPoolApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001573ObjectPoolApplication.class);

    public static void main(String[] args) {
        OliphauntPool pool = new OliphauntPool();
        LOGGER.info(pool.toString());
        Oliphaunt oliphaunt1 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt1);
        LOGGER.info(pool.toString());
        Oliphaunt oliphaunt2 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt2);
        Oliphaunt oliphaunt3 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt3);
        LOGGER.info(pool.toString());
        LOGGER.info("Checking in {}", oliphaunt1);
        pool.checkIn(oliphaunt1);
        LOGGER.info("Checking in {}", oliphaunt2);
        pool.checkIn(oliphaunt2);
        LOGGER.info(pool.toString());
        Oliphaunt oliphaunt4 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt4);
        Oliphaunt oliphaunt5 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt5);
        LOGGER.info(pool.toString());
    }

}
