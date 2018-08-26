package org.saxing.qisi.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * paramutil
 *
 * Created by saxing on 2018/5/6.
 */
public class SaParamsUtils {

    /**
     * 验证参数不为空
     *
     * @param params
     * @param args
     * @return
     */
    public static boolean paramIsNotNull(Map<String, Object> params, String[]...args){
        if (CollectionUtils.isEmpty(params)){
            return false;
        }
        if (args.length > 0){
            for (String[] arg : args){
                switch (arg[1]){
                    case StringConstant.STRING:
                        if (StringUtils.isEmpty(MapUtils.getString(params, arg[0], ""))){
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return true;
    }

    /**
     * 验证参数有空值
     *
     * @param params
     * @param args
     * @return
     */
    public static boolean paramHasNull(Map<String, Object> params, String[]...args){
        return !paramIsNotNull(params, args);
    }

}
