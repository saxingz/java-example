package org.saxing.qisi.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.saxing.qisi.config.WxProperties;
import org.saxing.qisi.dao.mapper.WxTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 微信token处理
 *
 * Created by saxing on 2018/5/5.
 */
@Service
public class WxTokenService {

    private final WxProperties wxProperties;
    private final WxTokenMapper wxTokenMapper;

    @Autowired
    public WxTokenService(WxProperties wxProperties, WxTokenMapper wxTokenMapper) {
        this.wxProperties = wxProperties;
        this.wxTokenMapper = wxTokenMapper;
    }

    /**
     * 刷新token
     * @return
     */
    public String downloadTokenFromWx(){
        wxProperties.getAgentId();
        wxProperties.getCorpId();
        wxProperties.getSecret();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String tokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wodecorpid&corpsecret=wodesecrect";
            tokenUrl = tokenUrl.replace("wodecorpid", wxProperties.getCorpId()).replace("wodesecrect", wxProperties.getSecret());
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

    /**
     * 保证数据库里有两个以内token
     */
    public void refreshToken(){
        int num = wxTokenMapper.queryTokenNum();
        if (num > 2){
            do {
                del1Token();
                num--;
            }while (num > 2);
        }else if (num < 2){
            //插入新token
            String token = downloadTokenFromWx();
            wxTokenMapper.insertToken(token);
        }
    }

    /**
     * 删除第一个token
     */
    public void del1Token(){
        wxTokenMapper.delete1stToken();

    }

    /**
     * 获取 token
     * @return
     */
    public String getToken(){
        return wxTokenMapper.get1stToken();
    }


}
