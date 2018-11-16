package org.saxing.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing  2018/11/16 16:41
 */
//@SpringBootApplication
public class Aa001513CachingApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Aa001513CachingApplication.class, args);
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001513CachingApplication.class);

    public static void main(String[] args) {
        AppManager.initDb(false);

        AppManager.initCacheCapacity(3);
        Aa001513CachingApplication app = new Aa001513CachingApplication();

        app.useReadAndWriteThroughStrategy();
        app.useReadThroughAndWriteAroundStrategy();
        app.useReadThroughAndWriteBehindStrategy();
        app.useCacheAsideStategy();
    }

    /**
     * Read-through and write-through
     */
    public void useReadAndWriteThroughStrategy(){
        LOGGER.info("# CachePolicy.THROUGH");
        AppManager.initCachingPolicy(CachingPolicy.THROUGH);

        UserAccount userAccount = new UserAccount("001", "John", "He is a boy");

        AppManager.save(userAccount);
        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("001");
        AppManager.find("001");
    }

    /**
     * Read-through and write-around
     */
    public void useReadThroughAndWriteAroundStrategy(){
        LOGGER.info("# CachingPolicy.AROUND");
        AppManager.initCachingPolicy(CachingPolicy.AROUND);

        UserAccount userAccount2 = new UserAccount("002", "Jane", "She is a girl.");

        AppManager.save(userAccount2);
        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("002");
        LOGGER.info(AppManager.printCacheContent());
        userAccount2 = AppManager.find("002");
        userAccount2.setUserName("Jane G.");
        AppManager.save(userAccount2);
        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("002");
        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("002");
    }


    /**
     * Read-through and write-behind
     */
    public void useReadThroughAndWriteBehindStrategy() {
        LOGGER.info("# CachingPolicy.BEHIND");
        AppManager.initCachingPolicy(CachingPolicy.BEHIND);

        UserAccount userAccount3 = new UserAccount("003", "Adam", "He likes food.");
        UserAccount userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
        UserAccount userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");

        AppManager.save(userAccount3);
        AppManager.save(userAccount4);
        AppManager.save(userAccount5);
        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("003");
        LOGGER.info(AppManager.printCacheContent());
        UserAccount userAccount6 = new UserAccount("006", "Yasha", "She is an only child.");
        AppManager.save(userAccount6);
        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("004");
        LOGGER.info(AppManager.printCacheContent());
    }

    /**
     * Cache-Aside
     */
    public void useCacheAsideStategy() {
        LOGGER.info("# CachingPolicy.ASIDE");
        AppManager.initCachingPolicy(CachingPolicy.ASIDE);
        LOGGER.info(AppManager.printCacheContent());

        UserAccount userAccount3 = new UserAccount("003", "Adam", "He likes food.");
        UserAccount userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
        UserAccount userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");
        AppManager.save(userAccount3);
        AppManager.save(userAccount4);
        AppManager.save(userAccount5);

        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("003");
        LOGGER.info(AppManager.printCacheContent());
        AppManager.find("004");
        LOGGER.info(AppManager.printCacheContent());
    }
}
