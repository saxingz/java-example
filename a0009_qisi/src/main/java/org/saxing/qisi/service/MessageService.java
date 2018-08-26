package org.saxing.qisi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.saxing.qisi.config.WxProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 * WxTuiSongService
 *
 * Created by saxing on 2018/5/20.
 */
@Slf4j
@Service
public class MessageService {

    private final WxProperties wxProperties;
    private final WxTokenService wxTokenService;

    public MessageService(WxProperties wxProperties, WxTokenService wxTokenService) {
        this.wxProperties = wxProperties;
        this.wxTokenService = wxTokenService;
    }


    /**
     * 发送图文
     * @param userId
     * @param digest
     * @param content
     * @param mediaId
     */
    public void sendNewsMessage(String userId, String digest, String content, String msgTitle, String contentSourceUrl, String mediaId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", wxTokenService.getToken());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type","application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", userId);
            jsonObject.put("msgtype", "mpnews");
            jsonObject.put("agentid", wxProperties.getAgentId());
            JSONObject mpnews = new JSONObject();
            JSONArray articles = new JSONArray();
            JSONObject aArticle = new JSONObject();
            aArticle.put("title", msgTitle);
            aArticle.put("thumb_media_id", mediaId);
            aArticle.put("content", content);
            aArticle.put("content_source_url", contentSourceUrl);
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
