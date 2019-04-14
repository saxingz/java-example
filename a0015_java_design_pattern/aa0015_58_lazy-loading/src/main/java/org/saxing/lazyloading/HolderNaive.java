package org.saxing.lazyloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HolderNaive
 *
 * @author saxing 2019/4/14 13:58
 */
public class HolderNaive {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolderNaive.class);

    private Heavy heavy;

    /**
     * Constructor
     */
    public HolderNaive() {
        LOGGER.info("HolderNaive created");
    }

    /**
     * Get heavy object
     */
    public Heavy getHeavy() {
        if (heavy == null) {
            heavy = new Heavy();
        }
        return heavy;
    }

}
