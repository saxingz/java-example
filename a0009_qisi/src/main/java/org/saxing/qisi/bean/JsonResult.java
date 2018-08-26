package org.saxing.qisi.bean;

import org.json.JSONObject;

/**
 * Created by saxing on 2018/5/6.
 */
public class JsonResult {

    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return jsonObject.toString();
    }

}
