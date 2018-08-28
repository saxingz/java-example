package org.saxing.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.saxing.bean.WxUser;
import org.saxing.config.WxProperties;
import org.saxing.dao.OrganizeDao;
import org.saxing.util.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * Created by saxing on 17-10-31.
 */
@Service
public class OrganizeService {

    private final WxProperties wxProperties;
    private final OrganizeDao organizeDao;

    @Autowired
    public OrganizeService(WxProperties wxProperties, OrganizeDao organizeDao) {
        this.wxProperties = wxProperties;
        this.organizeDao = organizeDao;
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
            departUrl = allDepartUrl.replace("ACCESS_TOKEN", WxUtils.getToken());
        }else{
            departUrl = oneDepartUrl.replace("ACCESS_TOKEN", WxUtils.getToken()).replace("wodeid", String.valueOf(id));
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
     * 获取 用户
     * @param userId
     * @return
     */
    public JSONObject downLoadUserFromWx(String userId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
        url = url.replace("ACCESS_TOKEN", WxUtils.getToken()).replace("USERID", userId);

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
     * 获取部门成员
     * @param id
     * @return
     */
    public JSONObject downLoadDepartUserFromWx(Integer id) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
        url = url.replace("ACCESS_TOKEN", WxUtils.getToken()).replace("DEPARTMENT_ID", String.valueOf(id)).replace("FETCH_CHILD", "0");

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
     * 获取部门和部门成员
     * @param id
     * @return
     */
    public JSONObject getDepartAndUser(Integer id) {
        JSONObject depart = this.downLoadDepartFromWx(id);
        JSONObject user = this.downLoadDepartUserFromWx(id);
        JSONObject result = new JSONObject();
        if ("ok".equals(depart.getString("errmsg")) || 0 == user.getInt("errcode")){
            result.put("errcode", 0);
            result.put("errmsg", "ok");
            //过滤非直接子部门
            result.put("department", filterDirectDepart(depart.getJSONArray("department"), id));

            //获取用户头像
            JSONArray userlist = user.getJSONArray("userlist");
            if (userlist.length() > 0){
                for (int i = 0; i < userlist.length(); i++) {
                    JSONObject aU = (JSONObject) userlist.get(i);
                    String userId = aU.getString("userid");
                    List<WxUser> wus = organizeDao.findUserById(userId);
                    if (CollectionUtils.isNotEmpty(wus) && wus.size() > 0){
                        WxUser wu = wus.get(0);
                        aU.put("avatar", wu.getAvatar());
                    }
                }
            }

            result.put("userlist", userlist);
        }else {
            result.put("errcode", -1);
            result.put("errmsg", "fail");
        }
        return result;
    }

    private JSONArray filterDirectDepart(JSONArray departs, int parentId){
        if (departs == null || departs.length() == 0){
            return null;
        }
        JSONArray resultArray = new JSONArray();
        for(int i = 0; i < departs.length(); i++){
            JSONObject temp = (JSONObject) departs.get(i);
            if (parentId == temp.getInt("parentid")){
                resultArray.put(temp);
            }
        }
        return resultArray;
    }

    /**
     * 刷新所有成员到数据库
     *
     * @return
     */
    @Transactional
    public void refreshAllUserId(){
        //清空
        organizeDao.emptyUser();

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
                                    String departStr = ((JSONObject)(depart.getJSONArray("userlist").get(j))).get("department").toString();
                                    WxUser wxUser = new WxUser(userId, name, departStr);
                                    //入库
                                    try {
                                        organizeDao.saveUser(wxUser);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }finally {
                                        //更新头像
                                        JSONObject personDetail = downLoadUserFromWx(userId);
                                        if (personDetail.has("errcode") && 0 == personDetail.getInt("errcode")){
                                            if (personDetail.has("avatar")){
                                                String avatar = personDetail.getString("avatar");
                                                if (StringUtils.isNotEmpty(avatar)){
                                                    wxUser.setAvatar(avatar);
                                                    try {
                                                        organizeDao.updateUser(wxUser);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("组织关系刷新成功");
    }

    /**
     * 获取所有的userId
     * @return
     */
    public List<String> getAllUserId(){
        return organizeDao.getAllUserId();
    }

}
