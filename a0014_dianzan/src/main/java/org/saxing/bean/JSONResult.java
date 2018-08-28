package org.saxing.bean;

import org.json.JSONObject;

/**
 * @author saxing
 * @description
 * @time 2017/10/30 0030 15:16
 */
public class JSONResult {

    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return jsonObject.toString();
    }

}
