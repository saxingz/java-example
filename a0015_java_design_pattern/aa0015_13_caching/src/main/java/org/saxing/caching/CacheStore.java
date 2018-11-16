package org.saxing.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    /**
     *
     * @param userId
     * @return
     */
    public static UserAccount readThrough(String userId){
        if (cache.contains(userId)){
            LOGGER.info("# Cache hit");
            return cache.get(userId);
        }
        LOGGER.info("# Cache miss");
        UserAccount userAccount = DbManager.readFromDb(userId);
        cache.set(userId, userAccount);
        return userAccount;
    }

    /**
     *
     * @param userAccount
     */
    public static void writeThrouth(UserAccount userAccount){
        if (cache.contains(userAccount.getUserId())){
            DbManager.updateDb(userAccount);
        } else {
            DbManager.writeToDb(userAccount);
        }
        cache.set(userAccount.getUserId(), userAccount);
    }

    /**
     *
     * @param userAccount
     */
    public static void writeAround(UserAccount userAccount){
        if (cache.contains(userAccount.getUserId())){
            DbManager.updateDb(userAccount);
            cache.invalidate(userAccount.getUserId());  // Cache data has been updated -- remove older
                                                        // version from cache.
        } else {
            DbManager.writeToDb(userAccount);
        }
    }

    /**
     * Get user account using read-through cache with write-back policy
     * @param userId
     * @return
     */
    public static UserAccount readThroughWithWriteBackPolicy(String userId){
        if (cache.contains(userId)){
            LOGGER.info("#Cache hit");
            return cache.get(userId);
        }
        LOGGER.info("# Cache Miss");
        UserAccount userAccount = DbManager.readFromDb(userId);
        if (cache.isFull()){
            LOGGER.info("# Cache is FULL! Writing LRU data to DB...");
            UserAccount toBeWrittenToDb = cache.getLruData();
            DbManager.upsertDb(toBeWrittenToDb);
        }
        cache.set(userId, userAccount);
        return userAccount;
    }

    /**
     * Set user account
     *
     * @param userAccount
     */
    public static void writeBehind(UserAccount userAccount){
        if (cache.isFull() && !cache.contains(userAccount.getUserId())){
            LOGGER.info("# Cache is full ! Write lru data to DB...");
            UserAccount toBeWritten = cache.getLruData();
            DbManager.upsertDb(toBeWritten);
        }
        cache.set(userAccount.getUserId(), userAccount);
    }

    /**
     * clear cache
     */
    public static void clearCache(){
        if (cache != null){
            cache.clear();
        }
    }

    /**
     * Writes remaining content in the cache into the DB.
     *
     */
    public static void flushCache(){
        LOGGER.info("# flushingCache...");
        if (null == cache){
            return;
        }
        List<UserAccount> listOfUserAccount = cache.getCacheDataInListForm();
        for (UserAccount userAccount : listOfUserAccount){
            DbManager.upsertDb(userAccount);
        }
    }

    /**
     * Print user accounts
     */
    public static String print(){
        List<UserAccount> listOfUserAccount = cache.getCacheDataInListForm();
        StringBuilder sb = new StringBuilder();
        sb.append("\n--CACHE CONTENT--\n");
        for (UserAccount account : listOfUserAccount){
            sb.append(account.toString() + "\n");
        }
        sb.append("----\n");
        return sb.toString();
    }

    /**
     *
     * @param userId
     * @return
     */
    public static UserAccount get(String userId){
        return cache.get(userId);
    }

    /**
     *
     * @param userId
     * @param userAccount
     */
    public void set(String userId, UserAccount userAccount){
        cache.set(userId, userAccount);
    }

    /**
     *
     * @param userId
     */
    public void invalidate(String userId){
        cache.invalidate(userId);
    }

}
