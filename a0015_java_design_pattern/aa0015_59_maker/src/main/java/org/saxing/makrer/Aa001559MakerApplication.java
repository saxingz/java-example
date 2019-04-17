package org.saxing.makrer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * main
 *
 * @author saxing 2019/4/17 20:59
 */
public class Aa001559MakerApplication {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Aa001559MakerApplication.class);
        Guard guard = new Guard();
        Thief thief = new Thief();

        if (guard instanceof Permission) {
            guard.enter();
        } else {
            logger.info("You have no permission to enter, please leave this area");
        }

        if (thief instanceof Permission) {
            thief.steal();
        } else {
            thief.doNothing();
        }
    }

}
