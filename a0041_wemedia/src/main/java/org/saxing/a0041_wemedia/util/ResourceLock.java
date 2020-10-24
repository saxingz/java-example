package org.saxing.a0041_wemedia.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * resource lock
 *
 * @author saxing 2019/4/3 16:20
 */
public class ResourceLock {

    private static final ConcurrentHashMap<String, AtomicInteger> lockMap = new ConcurrentHashMap<String, AtomicInteger>();

    public static AtomicInteger getAtomicInteger(String key) {
        if (lockMap.get(key) == null) {
            lockMap.putIfAbsent(key, new AtomicInteger(0));
        }
        int count = lockMap.get(key).incrementAndGet();
        return lockMap.get(key);
    }

    public static void giveUpAtomicInteger(String key) {
        if (lockMap.get(key) != null) {
            int source = lockMap.get(key).decrementAndGet();
            if (source <= 0) {
                lockMap.remove(key);
            }
        }
    }

    /**
     * 下载key
     * @return
     */
    public static String getDownloadKey(){
        return "DOWNLOAD_KEY";
    }

    /**
     * 重建视频key
     * @return
     */
    public static String getRebuildVideoKey(){
        return "RESET_VIDEO_KEY";
    }

}
