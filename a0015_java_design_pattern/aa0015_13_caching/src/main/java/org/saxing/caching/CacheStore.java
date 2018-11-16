package org.saxing.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The caching strategies are implemented in this class.
 */
public class CacheStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheStore.class);

    static LruCache cache;

    public CacheStore() {
    }

    /**
     * Init cache capacity
     *
     * @param capacity
     */
    public static void initCapacity(int capacity){
        if (cache == null){
            cache = new LruCache(capacity);
        } else {
            cache.setCapacity(capacity);
        }
    }


}
