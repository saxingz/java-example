package org.saxing.qisi.utils;

import java.util.UUID;

/**
 * uuid
 *
 * Created by saxing on 2018/5/6.
 */
public class SaIdUtils {

    /**
     * 获取 uuid
     * @return
     */
    public static String getSomeIds(){
        return UUID.randomUUID().toString();
    }

}
