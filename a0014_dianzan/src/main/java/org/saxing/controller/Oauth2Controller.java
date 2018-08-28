package org.saxing.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.saxing.bean.JSONResult;
import org.saxing.bean.WxUser;
import org.saxing.config.WxProperties;
import org.saxing.dao.OrganizeDao;
import org.saxing.service.OrganizeService;
import org.saxing.util.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

/**
 * oauth
 *
 * Created by saxing on 17-10-31.
 */
@Controller
@RequestMapping("/oauth")
public class Oauth2Controller {

    private final WxProperties wxProperties;
    private final OrganizeService organizeService;
    private final OrganizeDao organizeDao;

    @Autowired
    public Oauth2Controller( WxProperties wxProperties, OrganizeService organizeService, OrganizeDao organizeDao) {
        this.wxProperties = wxProperties;
        this.organizeService = organizeService;
        this.organizeDao = organizeDao;
    }

    @ApiOperation(value = "1.没有页面id时调用，2.巧妙解决历史记录问题")
    @GetMapping("/oauth_noid")
    public String getNoHistoryPage(){
        return "oauth";
    }

    /**
     * 获取当前用户id
     * @param request
     * @param code
     * @param state
     * @return
     */
    @ApiOperation(value = "获取当前用户id.[本方法不手动调用]", notes = "[本方法不手动调用]")
    @GetMapping(value = "/getCurrentUserId.do")
    public ModelAndView getCurrentUserId(HttpServletRequest request, String code, String state){
        if (StringUtils.isEmpty(code)){
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("index");

        String token = WxUtils.getToken();
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=wodeACCESS_TOKEN&code=wodecode";
        url = url.replace("wodeACCESS_TOKEN", token).replace("wodecode", code);

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
                    if (jsonObject.has("UserId")){
                        String userid = jsonObject.getString("UserId");

                        //获取部门id
                        List<WxUser> nowUserList = organizeDao.findUserById(userid);
                        String nowDepart = "";
                        if (CollectionUtils.isNotEmpty(nowUserList)){
                            WxUser nowUser = nowUserList.get(0);
                            String departs = nowUser.getUserDepartStr();
                            departs = departs.substring(1);
                            departs = departs.substring(0, departs.length() - 1);
                            System.out.println(departs);
                            String[] depArr = departs.split(",");
                            for (String d : depArr){
                                nowDepart += ("d" + d.trim() + "d");
                            }
                        }

                        String finalNowDepart = nowDepart;
                        modelAndView.addAllObjects(new HashMap<String, String>(){
                            {
                                put("myname", userid);
                                put("nowDepart", finalNowDepart);
                            }});
                        return modelAndView;
                    }else {
                        return new ModelAndView("oauth");
                    }

//                    return JSONResult.fillResultString(1, userid, null);

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
//        return JSONResult.fillResultString(-1, "获取userId失败", null);
        return modelAndView;
    }

    @ApiOperation(value = "oauth 获取当前用户id", notes = "返回当前登录网页的用户id， 直接调用，不传参数")
    @GetMapping(value = "/getOauth")
    public String getOauth(){
        String redirectUri = "http:" + wxProperties.getDomain() + "/oauth/getCurrentUserId.do";
        try {
            redirectUri = java.net.URLEncoder.encode(redirectUri, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=wodeappid" +
                "&redirect_uri=woderedirecturi" +
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&agentid=wodeagentid" +
                "&state=1#wechat_redirect";
        url = url.replace("woderedirecturi", redirectUri)
                .replace("wodeappid", wxProperties.getCorpID())
                .replace("wodeagentid", wxProperties.getAgentid());
        System.out.println("url: " + url);
        return "redirect:" + url;
    }

//    /**
//     * 构造参数并将请求重定向到微信API获取登录信息
//     *
//     * @param request
//     * @param resultUrl
//     * @return
//     */
//    @RequestMapping(value = { "/oauth2.do", "/oauth2" })
//    public String Oauth2API(HttpServletRequest request, @RequestParam String resultUrl) {
//        // 此处可以添加获取持久化的数据，如企业号id等相关信息
//        String CropId = "wx4d6e2a448c0f6e9a";
//        String agentId = "1000022";
//        String redirectUrl = "";
//        if (resultUrl != null) {
//            String reqUrl = "43usvp.natappfree.cc";
//            String backUrl ="http://" + reqUrl + "/oauth2url.do?oauth2url=" + resultUrl;
//            System.out.println("backUrl="+backUrl);
//            redirectUrl = oAuth2Url(CropId, agentId, resultUrl);
//        }
//        return "redirect:" + redirectUrl;
//    }
//
//    @RequestMapping("/test.do")
//    public void testOauth(HttpServletRequest request){
//        System.out.println(request);
//    }
//    @RequestMapping("/test2.do")
//    public void testOauth(HttpServletRequest request, String code){
//        System.out.println(request);
//        System.out.println(code);
//    }
//    @RequestMapping("/test3.do")
//    public void testOauth(HttpServletRequest request, String code, String state){
//        System.out.println(request);
//        System.out.println(code);
//        System.out.println(state);
//    }
//
//    /**
//     * 根据code获取Userid后跳转到需要带用户信息的最终页面
//     *
//     * @param request
//     * @param code
//     *            获取微信重定向到自己设置的URL中code参数
//     * @param oauth2url
//     *            跳转到最终页面的地址
//     * @return
//     */
//    @RequestMapping(value = { "/oauth2url.do" })
//    public String Oauth2MeUrl(HttpServletRequest request, @RequestParam String code, @RequestParam String oauth2url) {
//        System.out.println(code);
//        System.out.println(oauth2url);
////        AccessToken accessToken = QiYeUtil.getAccessToken(Constants.CORPID, Constants.Secret);
////        HttpSession session = request.getSession();
////        if (accessToken != null && accessToken.downloadTokenFromWx() != null) {
////            String Userid = getMemberGuidByCode(accessToken.downloadTokenFromWx(), code, Constants.AGENTID);
////            if (Userid != null) {
////                session.setAttribute("UserId", Userid);
////            }
////        }
////        // 这里简单处理,存储到session中
////        return "redirect:" + oauth2url;
//        return "saxing...";
//    }
//
//
//    /**
//     * 根据code获取Userid后跳转到需要带用户信息的最终页面
//     *
//     * @param request
//     *            跳转到最终页面的地址
//     * @return
//     */
//    @RequestMapping(value = { "/oauth2url3.do" })
//    public String Oauth2MeUrl(HttpServletRequest request) {
////        AccessToken accessToken = QiYeUtil.getAccessToken(Constants.CORPID, Constants.Secret);
////        HttpSession session = request.getSession();
////        if (accessToken != null && accessToken.downloadTokenFromWx() != null) {
////            String Userid = getMemberGuidByCode(accessToken.downloadTokenFromWx(), code, Constants.AGENTID);
////            if (Userid != null) {
////                session.setAttribute("UserId", Userid);
////            }
////        }
////        // 这里简单处理,存储到session中
////        return "redirect:" + oauth2url;
//        return "saxing...";
//    }
//
//    /**
//     * 根据code获取Userid后跳转到需要带用户信息的最终页面
//     *
//     * @param request
//     *            获取微信重定向到自己设置的URL中code参数
//     * @param oauth2url
//     *            跳转到最终页面的地址
//     * @return
//     */
//    @RequestMapping(value = { "/oauth2url2.do" })
//    public String Oauth2MeUrl(HttpServletRequest request,  @RequestParam String oauth2url) {
//        System.out.println(oauth2url);
////        AccessToken accessToken = QiYeUtil.getAccessToken(Constants.CORPID, Constants.Secret);
////        HttpSession session = request.getSession();
////        if (accessToken != null && accessToken.downloadTokenFromWx() != null) {
////            String Userid = getMemberGuidByCode(accessToken.downloadTokenFromWx(), code, Constants.AGENTID);
////            if (Userid != null) {
////                session.setAttribute("UserId", Userid);
////            }
////        }
////        // 这里简单处理,存储到session中
////        return "redirect:" + oauth2url;
//        return "saxing...";
//    }
//
//    /**
//     * 构造带员工身份信息的URL
//     *
//     * @param corpid
//     *            企业id
//     * @param redirect_uri
//     *            授权后重定向的回调链接地址，请使用urlencode对链接进行处理
////     * @param state
////     *            重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值
//     * @return
//     */
//    private String oAuth2Url(String corpid, String agentId, String redirect_uri) {
//        try {
//            redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + corpid + "&redirect_uri=" + redirect_uri
//                + "&response_type=code&scope=snsapi_base&agentid="+agentId+"&state=saxing#wechat_redirect";
//        System.out.println("oauth2Url=" + oauth2Url);
//        return oauth2Url;
//    }


}
