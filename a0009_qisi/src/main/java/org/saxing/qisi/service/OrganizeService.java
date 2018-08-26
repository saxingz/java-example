package org.saxing.qisi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.saxing.qisi.config.WxProperties;
import org.saxing.qisi.dao.mapper.OrganizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * organzie
 *
 * Created by saxing on 2018/5/6.
 */
@Slf4j
@Service
public class OrganizeService {

    private final OrganizeMapper organizeMapper;
    private final WxProperties wxProperties;
    private final WxTokenService wxTokenService;

    @Autowired
    public OrganizeService(OrganizeMapper organizeMapper, WxProperties wxProperties, WxTokenService wxTokenService) {
        this.organizeMapper = organizeMapper;
        this.wxProperties = wxProperties;
        this.wxTokenService = wxTokenService;
    }

    /**
     * 刷新所有成员数据
     */
    @Transactional
    public void refreshAllUserId() {
        //清空
        organizeMapper.clearWxUser();

        //获取全部部门
        JSONObject allDeparts = downLoadDepartFromWx(0);
        if (allDeparts.has("errcode")){
            if (0 == allDeparts.getInt("errcode")){
                JSONArray departs = allDeparts.getJSONArray("department");
                if (departs.length() > 0){
                    JSONObject depart = null;
                    for (int i = 0; i < departs.length(); i++){
                        System.out.println(departs.get(i));
                        depart = downLoadDepartUserFromWx(((JSONObject)departs.get(i)).getInt("id"));
                        if (depart.has("errcode") && depart.has("userlist")){
                            if (0 == depart.getInt("errcode") && depart.getJSONArray("userlist").length() > 0){
                                for (int j = 0; j < depart.getJSONArray("userlist").length(); j++) {
                                    String userId = ((JSONObject)(depart.getJSONArray("userlist").get(j))).getString("userid");
                                    String name = ((JSONObject)(depart.getJSONArray("userlist").get(j))).getString("name");
                                    JSONArray departIds = (JSONArray)(((JSONObject)(depart.getJSONArray("userlist").get(j))).get("department"));

                                    Map<String, Object> wUser = new HashMap<>();
                                    wUser.put("userId", userId);
                                    wUser.put("name", name);
                                    try {
                                        //入库
                                        organizeMapper.insertWxUser(wUser);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }finally {
                                        //更新头像
                                        JSONObject personDetail = downLoadUserFromWx(userId);
                                        if (personDetail.has("errcode") && 0 == personDetail.getInt("errcode")){
                                            if (personDetail.has("avatar")){
                                                String avatar = personDetail.getString("avatar");
                                                if (!StringUtils.isEmpty(avatar)){
                                                    wUser.put("avatar", avatar);
                                                    try {
                                                        organizeMapper.updateWxUser(wUser);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    //部门人员关系
                                    if (departIds.length() > 0){
                                        for (int k = 0; k < departIds.length(); k++){
                                            Integer tempDepartId = (Integer) departIds.get(k);
                                            Map<String, Object> departUserRelation = new HashMap<>();
                                            departUserRelation.put("departId", tempDepartId);
                                            departUserRelation.put("userId", userId);
                                            organizeMapper.insertDepartUserRelation(departUserRelation);
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
                log.info("组织关系更新成功");
            }
        }

    }

    /**
     * 根据ID获取部门， 0为全部部门
     * @param id
     * @return
     */
    public JSONObject downLoadDepartFromWx(int id){
        String allDepartUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN";
        String oneDepartUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=wodeid";
        String departUrl;
        if (id < 1){
            departUrl = allDepartUrl.replace("ACCESS_TOKEN", wxTokenService.getToken());
        }else{
            departUrl = oneDepartUrl.replace("ACCESS_TOKEN", wxTokenService.getToken()).replace("wodeid", String.valueOf(id));
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(departUrl);
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
                    return jsonObject;
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
        return null;
    }

    /**
     * 获取部门成员
     * @param id
     * @return
     */
    public JSONObject downLoadDepartUserFromWx(Integer id) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
        url = url.replace("ACCESS_TOKEN", wxTokenService.getToken()).replace("DEPARTMENT_ID", String.valueOf(id)).replace("FETCH_CHILD", "0");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
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
                    return jsonObject;
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

        return null;
    }

    /**
     * 获取 用户
     * @param userId
     * @return
     */
    public JSONObject downLoadUserFromWx(String userId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
        url = url.replace("ACCESS_TOKEN", wxTokenService.getToken()).replace("USERID", userId);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
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
                    return jsonObject;
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
        return null;
    }

    /**
     * 刷新所有部门数据
     */
    @Transactional
    public void refreshAllDepart() {
        JSONObject allDeparts = downLoadDepartFromWx(0);
        if (allDeparts.has("errcode")){
            if (0 == allDeparts.getInt("errcode")){
                //清空结构表
                organizeMapper.clearWxDepart();
                List<Map<String, Object>> allDepartsData = new ArrayList<>();

                JSONArray departs = allDeparts.getJSONArray("department");
                if (departs.length() > 0){
                    for (int i = 0; i < departs.length(); i++){
                        JSONObject depart = departs.getJSONObject(i);
                        String name = depart.getString("name");
                        int id = depart.getInt("id");
                        int parentId = depart.getInt("parentid");
                        int order = depart.getInt("order");
                        Map<String, Object> departData = new HashMap<>();
                        departData.put("name", name);
                        departData.put("id", id);
                        departData.put("parentId", parentId);
                        departData.put("order", order);
                        allDepartsData.add(departData);
                    }
                }
                if (allDepartsData.size() > 0){
                    organizeMapper.saveAllDeparts(allDepartsData);
                }

                //部门刷新完，刷新个人
                refreshAllUserId();
            }
        }
    }
}
