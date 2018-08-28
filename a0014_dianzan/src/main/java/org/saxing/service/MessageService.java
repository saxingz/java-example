package org.saxing.service;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.saxing.config.WxProperties;
import org.saxing.util.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saxing on 17-10-31.
 */
@Service
public class MessageService {

    private final WxProperties wxProperties;

    @Autowired
    public MessageService(WxProperties wxProperties) {
        this.wxProperties = wxProperties;
    }

    /**
     * 普通文字
     * @param userId
     * @param content
     */
    public void sendTextMessageToUser(String userId, String content){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getToken());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type","application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", userId);
            jsonObject.put("msgtype", "text");
            jsonObject.put("agentid", wxProperties.getAgentid());
            jsonObject.put("safe", 0);
            jsonObject.put("text", new JSONObject(){{put("content", content);}});

            httpPost.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("UTF-8")));

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK){
                
            }
            String responseContent = EntityUtils.toString(response.getEntity());
            System.out.println("responseContent: " + responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送卡片文字
     * @param userId
     * @param content
     */
    public void sendTextCardMessage(String userId, int thumb, String content){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getToken());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type","application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", userId);
            jsonObject.put("msgtype", "textcard");
            jsonObject.put("agentid", wxProperties.getAgentid());

            JSONObject textcard = new JSONObject();
            textcard.put("title", 1 == thumb ? "获赞通知" : "差评通知");
            textcard.put("description", content);
            textcard.put("url", "http://www.baidu.com");
            textcard.put("btntxt", "");

            jsonObject.put("textcard", textcard);

            httpPost.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("UTF-8")));

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK){

            }
            String responseContent = EntityUtils.toString(response.getEntity());
            System.out.println("responseContent: " + responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送图文
     * @param userId
     * @param thumb
     * @param digest
     * @param content
     */
    public void sendNewsMessage(String userId, int thumb, String digest, String content, String msgTitle){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getToken());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type","application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", userId);
            jsonObject.put("msgtype", "mpnews");
            jsonObject.put("agentid", wxProperties.getAgentid());
            JSONObject mpnews = new JSONObject();
            JSONArray articles = new JSONArray();
            JSONObject aArticle = new JSONObject();
            aArticle.put("title", msgTitle);
            aArticle.put("thumb_media_id",  1 == thumb ? wxProperties.getGoodMediaId() : wxProperties.getBadMediaId());
            aArticle.put("content", content);
            aArticle.put("digest", digest);
            articles.put(aArticle);
            mpnews.put("articles", articles);

            jsonObject.put("mpnews", mpnews);

            httpPost.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("UTF-8")));

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK){

            }
            String responseContent = EntityUtils.toString(response.getEntity());
            System.out.println("responseContent: " + responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
