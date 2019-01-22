package org.saxing.a0023_search.util;

import java.util.UUID;

/**
 * UUID utils
 *
 * @author saxing 2019/1/22 10:38
 */
public class UUIDUtils {

    /**
     * generate UUID without '-'
     *
     * @return
     */
    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
