package org.saxing.caching;

import java.text.ParseException;

/**
 * app manager
 *
 * AppManager helps to bridge the gap in communication between the main class and the application's
 *  * back-end. DB connection is initialized through this class. The chosen caching strategy/policy is
 *  * also initialized here. Before the cache can be used, the size of the cache has to be set.
 *  * Depending on the chosen caching policy, AppManager will call the appropriate function in the
 *  * CacheStore class.
 *
 * @author saxing  2018/11/16 16:22
 */
public class AppManager {

    private static CachingPolicy cachingPolicy;

    public AppManager() {
    }

    /**
     * Developer/Tester is able to choose whether the application should use MongoDB as its underlying
     *    * data storage or a simple Java data structure to (temporarily) store the data/objects during
     *    * runtime.
     *
     * @param userMongoDb
     */
    public static void initDb(boolean userMongoDb){
        if (userMongoDb){
            try {
                DbManager.connect();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            DbManager.createVirtualDb();
        }
    }

    /**
     *
     * @param policy
     */
    public static void initCachingPolicy(CachingPolicy policy){
        cachingPolicy  = policy;
        if (cachingPolicy == CachingPolicy.BEHIND){
            Runtime.getRuntime().addShutdownHook(new Thread(CacheStore::flushCache));
        }
        CacheStore.clearCache();
    }

    /**
     * initCacheCapacity
     * @param capacity
     */
    public static void initCacheCapacity(int capacity){
        CacheStore.initCapacity(capacity);
    }

    /**
     *
     * @param userId
     * @return
     */
    public static UserAccount find(String userId){
        if (cachingPolicy == CachingPolicy.THROUGH || cachingPolicy == CachingPolicy.AROUND){
            return CacheStore.readThrough(userId);
        } else if (cachingPolicy == CachingPolicy.BEHIND){
            return CacheStore.readThroughWithWriteBackPolicy(userId);
        } else if (cachingPolicy == CachingPolicy.ASIDE){
            return findAside(userId);
        }
        return null;
    }

    /**
     *
     * @param userAccount
     */
    public static void save(UserAccount userAccount){
        if (cachingPolicy == CachingPolicy.THROUGH){
            CacheStore.writeThrouth(userAccount);
        } else if (cachingPolicy == CachingPolicy.AROUND){
            CacheStore.writeAround(userAccount);
        } else if (cachingPolicy == CachingPolicy.BEHIND){
            CacheStore.writeBehind(userAccount);
        } else if (cachingPolicy == CachingPolicy.ASIDE){
            saveAside(userAccount);
        }
    }

    /**
     *
     * @return
     */
    public static String printCacheContent(){
        return CacheStore.print();
    }

    /**
     * Cache-Aside save user account helper
     *
     * @param userAccount
     */
    private static void saveAside(UserAccount userAccount){
        DbManager.updateDb(userAccount);
        CacheStore.invalidate(userAccount.getUserId());
    }

    /**
     *
     * @param userId
     */
    private static UserAccount findAside(String userId){
        UserAccount userAccount = CacheStore.get(userId);
        if (userAccount != null){
            return userAccount;
        }
        userAccount = DbManager.readFromDb(userId);
        if (userAccount != null){
            CacheStore.set(userId, userAccount);
        }
        return userAccount;
    }


}
