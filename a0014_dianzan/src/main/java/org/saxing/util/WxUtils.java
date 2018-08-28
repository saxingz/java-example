package org.saxing.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.saxing.config.WxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * wx工具
 *
 * Created by saxing on 17-10-31.
 */
@Component
public class WxUtils {

    private static JdbcTemplate jdbcTemplate;
    private static WxProperties wxProperties;

    @Autowired
    public WxUtils(JdbcTemplate jdbcTemplate, WxProperties wxProperties) {
        WxUtils.jdbcTemplate = jdbcTemplate;
        WxUtils.wxProperties = wxProperties;
    }


    /**
     * 保证数据库里有两个以内token
     */
    public static void refreshToken(){
        //
        String querySql = "SELECT count(1) FROM wx_token";
        String saveSql = "INSERT INTO wx_token(token) VALUES(?)";
        int num = jdbcTemplate.queryForObject(querySql, int.class);
        if (num > 2){
            do {
                del1Token();
                num--;
            }while (num > 2);
        }else if (num < 2){
            //插入新token
            String token = downloadTokenFromWx();
            jdbcTemplate.update(saveSql, token);
        }
    }

    /**
     * 删除第一个token
     */
    public static void del1Token(){
        String delSql = "DELETE FROM wx_token LIMIT 1";
        jdbcTemplate.update(delSql);

    }

    /**
     * 获取 token
     * @return
     */
    public static String getToken(){
        String sql = "select token from wx_token ORDER BY id desc LIMIT 1";
        String token = jdbcTemplate.queryForObject(sql, String.class);
        return token;
    }

    /**
     * 刷新token
     * @return
     */
    public static String downloadTokenFromWx(){
        wxProperties.getAgentid();
        wxProperties.getCorpID();
        wxProperties.getSecret();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String tokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wodecorpid&corpsecret=wodesecrect";
            tokenUrl = tokenUrl.replace("wodecorpid", wxProperties.getCorpID()).replace("wodesecrect", wxProperties.getSecret());
            HttpGet httpGet = new HttpGet(tokenUrl);
            //获取返回
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
//                    System.out.println("Response content: " + EntityUtils.toString(entity));
                    String responsecontent = EntityUtils.toString(entity);
                    // 打印响应内容
                    System.out.println(responsecontent);
                    JSONObject jsonObject = new JSONObject(responsecontent);
                    String token = jsonObject.getString("access_token");
                    return token;
                }
                System.out.println("------------------------------------");
            }finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "wrong in get token";
    }

}
