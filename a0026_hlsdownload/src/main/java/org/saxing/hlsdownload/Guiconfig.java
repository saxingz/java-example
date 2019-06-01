package org.saxing.hlsdownload;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

public class Guiconfig {

    public static void main(String[] args) {
        Integer serverPort = 18504;
        String method = "aes-256-cfb";
        Integer timeout = 5;

        String password = "";
        String[] servers = new String[]{
                "",
                ""
        };


        String json = "{\n" +
                "  \"configs\": [\n" +
                "  ],\n" +
                "  \"strategy\": null,\n" +
                "  \"index\": 4,\n" +
                "  \"global\": false,\n" +
                "  \"enabled\": true,\n" +
                "  \"shareOverLan\": false,\n" +
                "  \"isDefault\": false,\n" +
                "  \"localPort\": 1080,\n" +
                "  \"pacUrl\": null,\n" +
                "  \"useOnlinePac\": false,\n" +
                "  \"secureLocalPac\": true,\n" +
                "  \"availabilityStatistics\": false,\n" +
                "  \"autoCheckUpdate\": true,\n" +
                "  \"checkPreRelease\": false,\n" +
                "  \"isVerboseLogging\": false,\n" +
                "  \"logViewer\": {\n" +
                "    \"topMost\": false,\n" +
                "    \"wrapText\": false,\n" +
                "    \"toolbarShown\": false,\n" +
                "    \"Font\": \"Consolas, 8pt\",\n" +
                "    \"BackgroundColor\": \"Black\",\n" +
                "    \"TextColor\": \"White\"\n" +
                "  },\n" +
                "  \"proxy\": {\n" +
                "    \"useProxy\": false,\n" +
                "    \"proxyType\": 0,\n" +
                "    \"proxyServer\": \"\",\n" +
                "    \"proxyPort\": 0,\n" +
                "    \"proxyTimeout\": 3\n" +
                "  },\n" +
                "  \"hotkey\": {\n" +
                "    \"SwitchSystemProxy\": \"\",\n" +
                "    \"SwitchSystemProxyMode\": \"\",\n" +
                "    \"SwitchAllowLan\": \"\",\n" +
                "    \"ShowLogs\": \"\",\n" +
                "    \"ServerMoveUp\": \"\",\n" +
                "    \"ServerMoveDown\": \"\"\n" +
                "  }\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray array = new JSONArray();
        for (String server : servers){
            JSONObject temp = new JSONObject();
            temp.put("server", server);
            temp.put("server_port", serverPort);
            temp.put("password", password);
            temp.put("method", method);
            temp.put("plugin", "");
            temp.put("plugin_opts", "");
            temp.put("plugin_args", "");
            temp.put("remarks", "");
            temp.put("timeout", timeout);
            array.add(temp);
        }
        jsonObject.put("configs", array);

        System.out.println(jsonObject.toJSONString());
    }

}
