package org.saxing.service;

import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.saxing.bean.JSONResult;
import org.saxing.bean.ThumbRecord;
import org.saxing.config.WxProperties;
import org.saxing.dao.OrganizeDao;
import org.saxing.dao.ThumbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author saxing
 * @description
 * @time 2017/10/30 0030 16:34
 */
@Service
public class ThumbService {

    private final ThumbDao thumbDao;
    private final OrganizeService organizeService;
    private final OrganizeDao organizeDao;
    private MessageService messageService;
    private WxProperties wxProperties;

    @Autowired
    public ThumbService(ThumbDao thumbDao, OrganizeService organizeService, OrganizeDao organizeDao, MessageService messageService, WxProperties wxProperties) {
        this.thumbDao = thumbDao;
        this.organizeService = organizeService;
        this.organizeDao = organizeDao;
        this.messageService = messageService;
        this.wxProperties = wxProperties;
    }

    /**
     * 操作
     * @param fromUserId
     * @param toDepartmentId
     * @param toUserId
     * @param thumb
     * @param reason
     * @return
     */
    public String doThumb(String fromUserId, int toDepartmentId, String toUserId, int thumb, String reason) {
        if (StringUtils.isEmpty(fromUserId) || toDepartmentId < 1 || thumb == 0 ){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        if (StringUtils.isNotBlank(reason) && reason.length() > 85){
            reason = reason.substring(0, 85);
        }
        JSONObject fromUserJson = organizeService.downLoadUserFromWx(fromUserId);
        if (0 != fromUserJson.getInt("errcode")){
            return JSONResult.fillResultString(-1, "无点赞人信息，请重试", null);
        }

        ThumbRecord thumbRecord = new ThumbRecord();
        String fromUserName = fromUserJson.getString("name");
        //这里不考虑两个部门情况，所以取第一个部门
        JSONArray fromUserDepart = fromUserJson.getJSONArray("department");
        int fromDepartId = (int) fromUserDepart.get(0);
        JSONObject fromDepart = organizeService.downLoadDepartFromWx(fromDepartId);
        String fromDepartName = "";
        if (fromDepart.has("department")){
            JSONArray departs = fromDepart.getJSONArray("department");
            for(int i = 0; i < departs.length(); i++){
                if (fromDepartId == ((JSONObject)departs.get(i)).getInt("id")){
                    fromDepartName = ((JSONObject)departs.get(i)).getString("name");
                }
            }
        }

        //获取操作人信息
        thumbRecord.setFromUserId(fromUserId);
        thumbRecord.setFromUsername(fromUserName);
        thumbRecord.setFromDepartName(fromDepartName);
        thumbRecord.setFromPosition(fromUserJson.getString("position"));
        thumbRecord.setFromMobile(fromUserJson.getString("mobile"));
        thumbRecord.setFromGender(Objects.equals(fromUserJson.getString("gender"), "1") ? "男" : "女");
        thumbRecord.setFromEmail(fromUserJson.getString("email"));
        thumbRecord.setFromAvatar(fromUserJson.getString("avatar"));
        thumbRecord.setFromTelephone(fromUserJson.getString("telephone"));
        thumbRecord.setFromStatus(fromUserJson.getInt("status"));
        thumbRecord.setThumbUp(thumb);
        thumbRecord.setReason(reason);

        //查询部门信息
        thumbRecord.setToDepartmentId(toDepartmentId);

        JSONObject toDepartsJson = organizeService.downLoadDepartFromWx(toDepartmentId);
        JSONArray toDepartsArray = toDepartsJson.getJSONArray("department");
        if (toDepartsArray.length() == 0){
            return JSONResult.fillResultString(-1, "无被点赞部门信息，请重试", null);
        }

        JSONObject toDepartJson = null;
        for (int i = 0; i < toDepartsArray.length(); i++){
            JSONObject temp = (JSONObject) toDepartsArray.get(i);
            if (toDepartmentId == temp.getInt("id")){
                toDepartJson = temp;
                break;
            }
        }

        if (toDepartJson == null){
            return  JSONResult.fillResultString(-1, "无被点赞部门信息，请重试", null);
        }

        thumbRecord.setToDepartmentName(toDepartJson.getString("name"));


        if (StringUtils.isNotEmpty(toUserId)){
            JSONObject toUserJson = organizeService.downLoadUserFromWx(toUserId);
            if (0 != toUserJson.getInt("errcode")){
                return JSONResult.fillResultString(-1, "无被点赞人信息，请重试", null);
            }

            //被点赞人有值
            //查询被点赞人信息
            thumbRecord.setToUserId(toUserId);
            thumbRecord.setToIsleader(String.valueOf(toUserJson.getInt("isleader")));
            thumbRecord.setToUserName(toUserJson.getString("name"));
            thumbRecord.setToPosition(toUserJson.getString("position"));
            thumbRecord.setToMobile(toUserJson.getString("mobile"));
            thumbRecord.setToGender(Objects.equals(toUserJson.getString("gender"), "1") ? "男" : "女");
            thumbRecord.setToEmail(toUserJson.getString("email"));
            thumbRecord.setToAvatar(toUserJson.getString("avatar"));
            thumbRecord.setToTelephone(toUserJson.getString("telephone"));
            thumbRecord.setToStatus(toUserJson.getInt("status"));

            //2018 01 19 逻辑改变， 好评通知所有人， 差评逻辑不变
            if (thumb < 0){
                //通知差评  人
                wxSendToUserAndToMessageLeader(fromUserJson, toUserJson, toDepartJson, toUserJson, thumb, reason, 1);
            }else{
                //好评， 所有人都通知
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                JSONObject finalToDepartJson = toDepartJson;
                String finalReason = reason;
                wxSendToUserWithOutToLeader(fromUserJson, "@all", finalToDepartJson, toUserJson, thumb, finalReason, 1);
//                cachedThreadPool.execute(() -> {
//                    //获取全部id
//                    List<String> allUserId = organizeService.getAllUserId();
//                    if (CollectionUtils.isNotEmpty(allUserId)){
//                        for (String iUserId : allUserId){
//                            //防止过快
//                            try {
//                                Thread.sleep(50);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            wxSendToUserWithOutToLeader(fromUserJson, iUserId, finalToDepartJson, toUserJson, thumb, finalReason, 1);
//                        }
//                    }
//                });
            }
        }else {
            //2018 01 19 逻辑改变， 好评通知所有人， 差评逻辑不变
            if (thumb < 0){
                //通知差评  部门负责人
                wxSendToDepartLeader(fromUserJson, toDepartJson, toDepartJson, thumb, reason, 2);
            }else{
                //好评， 所有人都通知
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                JSONObject finalToDepartJson = toDepartJson;
                String finalReason1 = reason;
                wxSendToUserWithOutToLeader(fromUserJson, "@all", finalToDepartJson, finalToDepartJson, thumb, finalReason1, 2);
//                cachedThreadPool.execute(() -> {
//                    //获取全部id
//                    List<String> allUserId = organizeService.getAllUserId();
//                    if (CollectionUtils.isNotEmpty(allUserId)){
//                        for (String iUserId : allUserId){
//                            //防止过快
//                            try {
//                                Thread.sleep(50);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            wxSendToUserWithOutToLeader(fromUserJson, iUserId, finalToDepartJson, finalToDepartJson, thumb, finalReason1, 2);
//                        }
//                    }
//                });
            }
        }




        int result = thumbDao.saveThumbup(thumbRecord);
        if (result > 0){
            return JSONResult.fillResultString(1, "ok", null);
        }else {
            return JSONResult.fillResultString(-1, "fail", null);
        }
    }


    /**
     * 向个人发送通知, 同时向领导发送通知
     * @param fromUserJson
     * @param toUserJson
     * @param toDepartJson
     * @param realGetGood 实际获赞对象
     * @param thumb
     * @param reason
     * @param type 1=个人 2=部门，为了部门发， 就算有个人信息也忽略
     */
    private void wxSendToUserAndToMessageLeader(JSONObject fromUserJson,
                                                JSONObject toUserJson,
                                                JSONObject toDepartJson,
                                                JSONObject realGetGood,
                                                int thumb,
                                                String reason,
                                                int type){

        //差评不向自己发送消息, 只向领导发消息  2018.03.17
        if (1 != toUserJson.getInt("isleader")){
            wxSendToDepartLeader(fromUserJson, toDepartJson, realGetGood, thumb, reason, 1);
        }else{
            wxSendToUserWithOutToLeader(fromUserJson, toUserJson.getString("userid"), toDepartJson, realGetGood, thumb, reason, type);
        }
    }

    /**
     * 向个人发送通知, 但  [不会] 向领导发送通知
     * @param fromUserJson
     * @param receiveUserid  接收消息的用户id
     * @param toDepartJson
     * @param realGetGood 实际获赞对象
     * @param thumb
     * @param reason
     * @param type 1=个人 2=部门，为了部门发， 就算有个人信息也忽略
     */
    private void wxSendToUserWithOutToLeader(JSONObject fromUserJson,
                                             String receiveUserid,
                                             JSONObject toDepartJson,
                                             JSONObject realGetGood,
                                             int thumb,
                                             String reason,
                                             int type){
        //如果是负责人， 则不向部门领导发送通知，否则通知部门领导
        String msgContent = "";
        String digest = "";
        String msgTitle = "";
        Integer fromDepartId = (Integer) fromUserJson.getJSONArray("department").get(0);
        //查找部门
        String fromDepartName = organizeDao.getDepartName(fromDepartId);
        // 做第二个保险
        if (StringUtils.isEmpty(fromDepartName)){
            JSONObject departFromWx = organizeService.downLoadDepartFromWx(fromDepartId);
            if (departFromWx.has("department")){
                JSONArray departs = departFromWx.getJSONArray("department");
                if (departs.length() > 0){
                    JSONObject depart = (JSONObject) departs.get(0);
                    if (depart.has("name")){
                        fromDepartName = (String) depart.get("name");
                    }
                }
            }
        }

        if (1 == thumb){
            msgTitle = (1 == type ? (toDepartJson.getString("name") + " " + realGetGood.getString("name"))
                    : realGetGood.getString("name")) + " 棒棒的！";
            digest = fromUserJson.getString("name") + " 对 " + (1 == type ? (toDepartJson.getString("name") + " " + realGetGood.getString("name"))
                    : realGetGood.getString("name")) + " 的点赞！";
            msgContent = getMsgHtmlContent().replace("{wx.title}", "获赞通知")
                    .replace("{wx.img}", wxProperties.getDomain() + "/good.jpeg")
//                    .replace("{wx.img}", "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_redBlue.png")
                    .replace("{wx.content1}", "又双叒叕被点赞啦，<br/>太过优秀真是不好意思啊！<br/>")
                    .replace("{wx.content2}", "<font color=\"#ff4500\">" + fromDepartName + " " + fromUserJson.getString("name") + "</font> 对 <font color=\"#ff4500\">"
                            + (1 == type ? (toDepartJson.getString("name") + " " + realGetGood.getString("name"))
                            : realGetGood.getString("name")) + "</font> 进行了点赞。")
                    .replace("{wx.content3}", StringUtils.isEmpty(reason) ? "" : "原因是：")
                    .replace("{wx.content4}", StringUtils.isEmpty(reason) ? "" : reason)
                    .replace("{wx.href}", "http:" + wxProperties.getIndex());

        }else{
            msgTitle = "差评通知";
            digest = "惨啦惨啦, 被差评啦~";
//            msgContent = getMsgHtmlContent().replace("{wx.title}", "差评通知")
//                    .replace("{wx.img}", wxProperties.getDomain() + "/bad.jpg")
//                    .replace("{wx.content1}", "被差评了。。。")
//                    .replace("{wx.content2}", "有人匿名 对 <font color=\"#ff4500\">"
//                            + (1 == type ? (toDepartJson.getString("name") + " "  + realGetGood.getString("name"))
//                            : realGetGood.getString("name")) + " </font> 进行了差评。<br/>")
//                    .replace("{wx.content3}", StringUtils.isEmpty(reason) ? "" : "原因是：")
//                    .replace("{wx.content4}", StringUtils.isEmpty(reason) ? "" : reason)
//                    .replace("{wx.href}", "http:" + wxProperties.getIndex());

            msgContent = getMsgHtmlContent().replace("{wx.title}", "差评通知")
                    .replace("{wx.img}", wxProperties.getDomain() + "/bad.jpg")
                    .replace("{wx.content1}", "")
                    .replace("{wx.content2}", "")
                    .replace("{wx.content3}", "")
                    .replace("{wx.content4}", toDepartJson.getString("name") + "收到差评 1 条。")
                    .replace("{wx.href}", "http:" + wxProperties.getIndex());
        }
        System.out.println("网页内容：");
        System.out.println(msgContent);
        messageService.sendNewsMessage(receiveUserid, thumb, digest, msgContent, msgTitle);
    }

    /**
     * 向领导发送消息
     * @param fromUserJson
     * @param toDepartJson
     * @param realGetGood 实际获赞对象
     * @param thumb
     * @param reason
     * @param type 1=从个人角度进入， 有明确点赞对象， 2=对部门点赞
     */
    public void wxSendToDepartLeader(JSONObject fromUserJson, JSONObject toDepartJson, JSONObject realGetGood, int thumb, String reason, int type){
        //取出部门所有人，找领导
        JSONObject users = organizeService.downLoadDepartUserFromWx(toDepartJson.getInt("id"));
        JSONArray userlist = users.getJSONArray("userlist");
        if (userlist.length() == 0){
            return;
        }
        String userid;
        for (int i = 0; i < userlist.length(); i++){
            userid = ((JSONObject)userlist.get(i)).getString("userid");
            //查询是否是leader
            JSONObject user = organizeService.downLoadUserFromWx(userid);
            int isleader = user.getInt("isleader");
            if (1 == isleader){
                if (1==type){
                    wxSendToUserAndToMessageLeader(fromUserJson, user, toDepartJson, realGetGood, thumb, reason, 1);
                }else {
                    wxSendToUserAndToMessageLeader(fromUserJson, user, toDepartJson, realGetGood, thumb, reason, 2);
                }
                break;
            }
        }
    }

    private String getMsgHtmlContent(){
//        return "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>{wx.title}</title><style type=\"text/css\">\t\thtml { line-height: 1.15; -ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;}\tbody {margin: 0;min-width:320px; background:#fff;}\t\tarticle,\taside,\tfooter,\theader,\tnav,\tsection {\t  display: block;\t}\t\th1 {\t  font-size: 2em;\t  margin: 0.67em 0;\t}\t\t\tfigcaption,\tfigure,\tmain {\t  \t  display: block;\t}\t\tfigure {\t  margin: 1em 40px;\t}\t\thr {\t  box-sizing: content-box;\t  \t  height: 0;\t  \t  overflow: visible;\t  \t}\t\tpre {\t  font-family: monospace, monospace;\t  \t  font-size: 1em;\t  \t}\t\t\ta {\t  background-color: transparent;\t  \t  -webkit-text-decoration-skip: objects;\t  \t}\t\tabbr[title] {\t  border-bottom: none;\t  \t  text-decoration: underline;\t  \t  text-decoration: underline dotted;\t  \t}\t\tb,\tstrong {\t  font-weight: inherit;\t}\t\tb,\tstrong {\t  font-weight: bolder;\t}\t\tcode,\tkbd,\tsamp {\t  font-family: monospace, monospace;\t  \t  font-size: 1em;\t  \t}\t\tdfn {\t  font-style: italic;\t}\t\tmark {\t  background-color: #ff0;\t  color: #000;\t}\t\tsmall {\t  font-size: 80%;\t}\t\tsub,\tsup {\t  font-size: 75%;\t  line-height: 0;\t  position: relative;\t  vertical-align: baseline;\t}\tsub {\t  bottom: -0.25em;\t}\tsup {\t  top: -0.5em;\t}\t\t\taudio,\tvideo {\t  display: inline-block;\t}\t\taudio:not([controls]) {\t  display: none;\t  height: 0;\t}\t\timg {\t  border-style: none;\t}\t\tsvg:not(:root) {\t  overflow: hidden;\t}\t\t\tbutton,\tinput,\toptgroup,\tselect,\ttextarea {\t  font-family: sans-serif;\t  \t  font-size: 100%;\t  \t  line-height: 1.15;\t  \t  margin: 0;\t  \t}\t\tbutton,\tinput {\t  \t  overflow: visible;\t}\t\tbutton,\tselect {\t  \t  text-transform: none;\t}\t\tbutton,\thtml [type=\"button\"],\t[type=\"reset\"],\t[type=\"submit\"] {\t  -webkit-appearance: button;\t  \t}\t\tbutton::-moz-focus-inner,\t[type=\"button\"]::-moz-focus-inner,\t[type=\"reset\"]::-moz-focus-inner,\t[type=\"submit\"]::-moz-focus-inner {\t  border-style: none;\t  padding: 0;\t}\t\tbutton:-moz-focusring,\t[type=\"button\"]:-moz-focusring,\t[type=\"reset\"]:-moz-focusring,\t[type=\"submit\"]:-moz-focusring {\t  outline: 1px dotted ButtonText;\t}\t\tfieldset {\t  padding: 0.35em 0.75em 0.625em;\t}\t\tlegend {\t  box-sizing: border-box;\t  \t  color: inherit;\t  \t  display: table;\t  \t  max-width: 100%;\t  \t  padding: 0;\t  \t  white-space: normal;\t  \t}\t\tprogress {\t  display: inline-block;\t  \t  vertical-align: baseline;\t  \t}\t\ttextarea {\t  overflow: auto;\t}\t\t[type=\"checkbox\"],\t[type=\"radio\"] {\t  box-sizing: border-box;\t  \t  padding: 0;\t  \t}\t\t[type=\"number\"]::-webkit-inner-spin-button,\t[type=\"number\"]::-webkit-outer-spin-button {\t  height: auto;\t}\t\t[type=\"search\"] {\t  -webkit-appearance: textfield;\t  \t  outline-offset: -2px;\t  \t}\t\t[type=\"search\"]::-webkit-search-cancel-button,\t[type=\"search\"]::-webkit-search-decoration {\t  -webkit-appearance: none;\t}\t\t::-webkit-file-upload-button {\t  -webkit-appearance: button;\t  \t  font: inherit;\t  \t}\t\t\tdetails,\tmenu {\t  display: block;\t}\t\tsummary {\t  display: list-item;\t}\t\t\tcanvas {\t  display: inline-block;\t}\t\ttemplate {\t  display: none;\t}\t\t\t[hidden] {\t  display: none;\t}\tbody,\th1,\th2,\th3,\th4,\th5,\th6,\thr,\tp,\tdl,\tdt,\tdd,\tul,\tol,\tli,\tform,\tfieldset,\tlegend,\tbutton,\tinput,\ttextarea,\tth,\ttd {\t  margin: 0;\t  padding: 0;\t}\tbody,\tbutton,\tinput,\tselect,\ttextarea {\t  font: 12px/1.5 arial, tahoma;\t  color: #333;\t}\tbutton,\tinput,\tselect,\ttextarea {\t  font-family: \"Microsoft YaHei\";\t}\th1,\th2,\th3,\th4,\th5,\th6 {\t  font-size: 100%;\t}\tem,\ti {\t  font-style: normal;\t}\tfieldset,\timg {\t  border: 0;\t}\timg {\t  vertical-align: top;\t}\tul,\tol {\t  list-style: none;\t}\ta {\t  color: #2272CB;\t  text-decoration: none;\t  outline: none;\t}\ta:hover {\t  color: #F70;\t  text-decoration: underline;\t}\t.clear {\t  clear: both;\t  display: block;\t  height: 0;\t  overflow: hidden;\t  font-size: 0;\t}\tbutton,\tinput,\tselect,\ttextarea {\t  font-size: 100%;\t  outline: none;\t}\ttable {\t  border-collapse: collapse;\t  border-spacing: 0;\t}\tbody {\t  font: 14px/1.5 'Microsoft YaHei';\t}\thtml,\tbody {\t  overflow: hidden;\t  word-break: break-all;\t}\tinput {\t  box-sizing: inherit;\t}\t\t.clearfix:after {\t  content: \".\";\t  display: block;\t  height: 0;\t  clear: both;\t  visibility: hidden;\t}\t.clearfix {\t  zoom: 1;\t}\t@media screen and (min-width:200px) {\t  html {\t  font-size:20px\t}\tbody {\t  font-size:12px\t}\t}@media screen and (min-width:320px) {\t  html {\t  font-size:21.33px\t}\tbody {\t  font-size:12px\t}\t}@media screen and (min-width:360px) {\t  html {\t  font-size:24px\t}\tbody {\t  font-size:12px\t}\t}@media screen and (min-width:375px) {\t  html {\t  font-size:25px\t}\tbody {\t  font-size:12px\t}\t}@media screen and (min-width:384px) {\t  html {\t  font-size:25.6px\t}\tbody {\t  font-size:14px\t}\t}@media screen and (min-width:400px) {\t  html {\t  font-size:26.67px\t}\tbody {\t  font-size:14px\t}\t}@media screen and (min-width:414px) {\t  html {\t  font-size:27.6px\t}\tbody {\t  font-size:14px\t}\t}@media screen and (min-width:424px) {\t  html {\t  font-size:28.27px\t}\tbody {\t  font-size:14px\t}\t}@media screen and (min-width:480px) {\t  html {\t  font-size:32px\t}\tbody {\t  font-size:15.36px\t}\t}@media screen and (min-width:540px) {\t  html {\t  font-size:36px\t}\tbody {\t  font-size:17.28px\t}\t}@media screen and (min-width:720px) {\t  html {\t  font-size:48px\t}\tbody {\t  font-size:23.04px\t}\t}@media screen and (min-width:750px) {\t  html {\t  font-size:50px\t}\tbody {\t  font-size:24px\t}\t}\t    \t.wrap{padding:1rem; font-size:0.8rem;}\t\t.wrap .img{ text-align:center; margin-bottom:1rem;}\t\t.wrap .img img{ max-width:80%;}\t\t.wrap p{font-size:0.8rem; color:#333; text-indent:1.7rem; line-height:1.5rem; margin-bottom:0.5rem;}\t\t.wrap h5{font-size:1rem; font-weight:700;}\t\t.wrap a{}</style></head><body><div class=\"wrap\"><div class=\"img\"><img src=\"{wx.img}\"/></div><p>{wx.content1}</p><p>{wx.content2}</p><h5>{wx.content3}</h5><p>{wx.content4}</p><a href=\"{wx.href}\"><h2>参与点赞/差评</h2></a></div></body></html>";
        return "<meta charset=\"UTF-8\"><body><div class=\"wrap\"><div class=\"img\"></div><p>{wx.content1}</p><p>{wx.content2}</p><h5>{wx.content3}</h5><p>{wx.content4}</p><a href=\"{wx.href}\"><h2>参与点赞/差评</h2></a></div></body></html>";
    }

    /**
     * 获取报表， 不包含支行和行长室
     * @param year
     * @param month
     * @return
     */
    public String getReport(int year, int month) {
        List<Map<String, Object>> list = this.getDepartCount(year*100+month);
        //排除行长室 和 所有支行
        List<Map<String, Object>> res = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            for (Map<String, Object> li : list) {
                if (MapUtils.getString(li, "depart_name", "").contains("支行")
                        || MapUtils.getString(li, "depart_name", "").contains("行长")){
                    continue;
                }
                res.add(li);
            }
        }
        return JSONResult.fillResultString(1, "ok", res);
    }

    /**
     * 获取报表， 只包含支行
     * @param year
     * @param month
     * @return
     */
    public String getZhihReport(int year, int month) {
        List<Map<String, Object>> list = this.getDepartCount(year*100+month);
        //排除行长室 和 所有支行
        List<Map<String, Object>> res = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            for (Map<String, Object> li : list) {
                if (MapUtils.getString(li, "depart_name", "").contains("支行")){
                    res.add(li);
                }
            }
        }
        return JSONResult.fillResultString(1, "ok", res);
    }

    /**
     * 获取年度部门报表
     * @param year
     * @return
     */
    public String getYearDepartReport(int year) {
        int start = year * 100;
        int end = start + 100;
        List<Map<String, Object>> list = thumbDao.getYearCount(start, end);
        //排除行长室 和 所有支行
        List<Map<String, Object>> res = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            for (Map<String, Object> li : list) {
                if (MapUtils.getString(li, "depart_name", "").contains("支行")
                        || MapUtils.getString(li, "depart_name", "").contains("行长")){
                    continue;
                }
                res.add(li);
            }
        }
        return JSONResult.fillResultString(1, "ok", res);
    }

    /**
     * 获取年度支行报表
     * @param year
     * @return
     */
    public String getZhihYearDepartReport(int year) {
        int start = year * 100;
        int end = start + 100;
        List<Map<String, Object>> list = thumbDao.getYearCount(start, end);
        //只要所有支行
        List<Map<String, Object>> res = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            for (Map<String, Object> li : list) {
                if (MapUtils.getString(li, "depart_name", "").contains("支行")){
                    res.add(li);
                }
            }
        }
        return JSONResult.fillResultString(1, "ok", res);
    }

    /**
     * 获取总榜
     * @return
     */
    public String getTotalReport(){
        List<Map<String, Object>> list = thumbDao.getTotalDepartCount();
        //排除行长室 和 所有支行
        List<Map<String, Object>> res = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            for (Map<String, Object> li : list) {
                if (MapUtils.getString(li, "depart_name", "").contains("支行")
                        || MapUtils.getString(li, "depart_name", "").contains("行长")){
                    continue;
                }
                res.add(li);
            }
        }
        return JSONResult.fillResultString(1, "ok", res);
    }

    /**
     * 获取记录
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ThumbRecord> getThumb(String startDate, String endDate){
        return thumbDao.getThumb(startDate, endDate);
    }


    /**
     * 获取最新10条
     * @return
     */
    public String getLastGood10() {
        List<ThumbRecord> records = thumbDao.getLastGood10();
        return dealData(records);
    }

    private String dealData(List<ThumbRecord> records){
        JSONArray array = new JSONArray(records);
        JSONArray resultArray = new JSONArray();
        JSONObject temp = null;
        JSONObject thumbRecordTemp;
        if (array.length() == 0){
            return JSONResult.fillResultString(1, "ok", resultArray);
        }
        for (int i = 0; i < array.length(); i++){
            temp = new JSONObject();
            thumbRecordTemp = ((JSONObject)array.get(i));
            temp.put("fromUserName", thumbRecordTemp.getString("fromUsername"));
            String toUserName = "";
            if (thumbRecordTemp.has("fromDepartName")){
                String fromDepartName = thumbRecordTemp.getString("fromDepartName");
                temp.put("fromDepartName", fromDepartName);
            }
            if (thumbRecordTemp.has("toUserName")){
                toUserName = thumbRecordTemp.getString("toUserName");
            }

            temp.put("toUserName", toUserName);
            temp.put("toDepartmentName", thumbRecordTemp.getString("toDepartmentName"));
            String datetime = thumbRecordTemp.getString("createTime");
            if (datetime.length() > 19){
                datetime = datetime.substring(0, 19);
            }
            temp.put("date", datetime);
            temp.put("fromHeadPic", thumbRecordTemp.getString("fromAvatar"));
            temp.put("reason", thumbRecordTemp.getString("reason"));

            resultArray.put(temp);
        }
        return JSONResult.fillResultString(1, "ok", resultArray);
    }

    public List<Map<String, Object>> getDepartCount(int yearMonth){
        if (yearMonth < 100000 || yearMonth > 1000000){
            return null;
        }
        return thumbDao.getDepartCount(yearMonth);
    }

    public void delDepartCount(int yearMonth) {
        thumbDao.delDepartCount(yearMonth);
    }

    public void saveDepartCount(Map<String, Object> result){
        int departId = MapUtils.getIntValue(result, "departId");
        String departName = MapUtils.getString(result, "departName");
        int good = MapUtils.getIntValue(result, "good");
        int bad = MapUtils.getIntValue(result, "bad");
        int yearMonth = MapUtils.getIntValue(result, "yearMonth");
        thumbDao.saveDepartCount(departId, departName, good, bad, yearMonth);
    }


    /**
     * 获取指定部门点赞详情
     * @param departId
     * @param year
     * @param month
     * @return
     */
    public String getGoodDetailByDepartAndMonth(int thumb, int year, int month, int departId) {
        LocalDateTime searchDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime beforeDate = searchDate.minusSeconds(1);
        LocalDateTime afterDate = searchDate.plusMonths(1);
        String startDate = beforeDate.toString();
        String endDate = afterDate.toString();
        List<ThumbRecord> records = thumbDao.getByDepartIdAndMonth(thumb, startDate, endDate, departId);
        return dealData(records);
    }


    /**
     * 年度部门员工统计排名
     * @param thumb
     * @param year
     * @param departId
     * @return
     */
    public String getPersonRankInYearService(int thumb, int year, int departId){
        LocalDateTime searchDate = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime beforeDate = searchDate.minusSeconds(1);
        LocalDateTime afterDate = searchDate.plusYears(1);
        String startDate = beforeDate.toString();
        String endDate = afterDate.toString();
        List<Map<String, Object>> res = thumbDao.getPersonRank(thumb, departId, startDate, endDate);
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    /**
     * 月度部门员工统计排名
     * @param thumb
     * @param year
     * @param month
     * @param departId
     * @return
     */
    public String getPersonRankInYearMonthService(int thumb, int year, int month, int departId){
        LocalDateTime searchDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime beforeDate = searchDate.minusSeconds(1);
        LocalDateTime afterDate = searchDate.plusMonths(1);
        String startDate = beforeDate.toString();
        String endDate = afterDate.toString();
        List<Map<String, Object>> res = thumbDao.getPersonRank(thumb, departId, startDate, endDate);
        Gson gson = new Gson();
        return gson.toJson(res);
    }

}
