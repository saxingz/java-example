package org.saxing.lazyloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Same as HolderNaive but with added synchronization. This implementation is thread safe, but each
 *  * {@link #getHeavy()} call costs additional synchronization overhead.
 *
 *  @author saxing 2019/4/14 13:58
 */
public class HolderThreadSafe {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolderThreadSafe.class);

    private Heavy heavy;

    /**
     * Constructor
     */
    public HolderThreadSafe() {
        LOGGER.info("HolderThreadSafe created");
    }

    /**
     * Get heavy object
     */
    public synchronized Heavy getHeavy() {
        if (heavy == null) {
            heavy = new Heavy();
        }
        return heavy;
    }

}
