package org.saxing.lazyloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * lazy loading
 *
 * @author saxing 2019/4/14 13:55
 */
public class Aa001558LazyLoadingApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001558LazyLoadingApplication.class);

    public static void main(String[] args) {
        // Simple lazy loader - not thread safe
        HolderNaive holderNaive = new HolderNaive();
        Heavy heavy = holderNaive.getHeavy();
        LOGGER.info("heavy={}", heavy);

        // Thread safe lazy loader, but with heavy synchronization on each access
        HolderThreadSafe holderThreadSafe = new HolderThreadSafe();
        Heavy another = holderThreadSafe.getHeavy();
        LOGGER.info("another={}", another);

        // The most efficient lazy loader utilizing Java 8 features
        Java8Holder java8Holder = new Java8Holder();
        Heavy next = java8Holder.getHeavy();
        LOGGER.info("next={}", next);
    }

}
