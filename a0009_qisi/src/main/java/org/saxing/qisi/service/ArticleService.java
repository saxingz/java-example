package org.saxing.qisi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.saxing.qisi.bean.JsonResult;
import org.saxing.qisi.config.WxProperties;
import org.saxing.qisi.dao.mapper.ArticleMapper;
import org.saxing.qisi.dao.mapper.OrganizeMapper;
import org.saxing.qisi.utils.SaParamsUtils;
import org.saxing.qisi.utils.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * article service
 *
 * Created by saxing on 2018/5/6.
 */
@Slf4j
@Service
public class ArticleService {


    @Value("${img.imgAccess}")
    private String imgAccess;

    private final OrganizeMapper organizeMapper;
    private final ArticleMapper articleMapper;
    private final MessageService messageService;
    private final WxProperties wxProperties;

    @Autowired
    public ArticleService(OrganizeMapper organizeMapper, ArticleMapper articleMapper, MessageService messageService, WxProperties wxProperties) {
        this.organizeMapper = organizeMapper;
        this.articleMapper = articleMapper;
        this.messageService = messageService;
        this.wxProperties = wxProperties;
    }

    private final String qiSiTemplate = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no\" />\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1><span style=\"color: red;\">{{departAndUser}}</span> 有了奇思妙想~</h1><br>\n" +
            "<h1>{{topic}}</h1><br>\n" +
            "<h1>速速点击阅读原文进行查看~</h1>\n" +
            "</body>\n" +
            "</html>";

    private final String tuCaoTemplate = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no\" />\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1><span style=\"color: red;\">{{departAndUser}}</span> 吐槽~</h1><br>\n" +
            "<h1>{{topic}}</h1><br>\n" +
            "<h1>速速点击阅读原文进行查看~</h1>\n" +
            "</body>\n" +
            "</html>";

    /**
     * 发布文章
     * @param params
     * @return
     */
    @Transactional
    public String publishArticle(Map<String, Object> params){
        String wxId = MapUtils.getString(params, StringConstant.WX_USER_ID, "");
        if (StringUtils.isEmpty(wxId)){
            return JsonResult.fillResultString(-1, "wrong params", null);
        }
        //检测是系统里的人
        Map<String, Object> wxUser = organizeMapper.findWxUserById(wxId);
        if (MapUtils.isEmpty(wxUser)){
            return JsonResult.fillResultString(-1, "not in person", null);
        }
        //插入文章
        int res = articleMapper.insertArticle(params);
        if (res > 0){
            //如果有图片，记录图片地址
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> images = (List<Map<String, Object>>) MapUtils.getObject(params, StringConstant.IMAGES);
            //插入数据库
            if (CollectionUtils.isNotEmpty(images)){
                for (Map<String, Object> img : images){
                    if (!SaParamsUtils.paramIsNotNull(img,
                            new String[]{StringConstant.IMAGE_ORI_NAME, StringConstant.STRING},
                            new String[]{StringConstant.IMAGE_UID_NAME, StringConstant.STRING},
                            new String[]{StringConstant.IMAGE_INDEX, StringConstant.STRING})){
                        log.error("图片参数错误");
                        return JsonResult.fillResultString(-1, "wrong img params", null);
                    }
                    img.put(StringConstant.IMAGE_ARTICLE_ID, MapUtils.getIntValue(params, StringConstant.ARTICLE_ID));
                    articleMapper.insertImage(img);
                }
            }

            int type = MapUtils.getIntValue(params, StringConstant.ARTICLE_TYPE, 1);
            if (0 == type){
                //推送
                publishMessage(wxUser, params, qiSiTemplate, wxProperties.getMessageImg(), "想法");
            }else if (1 == type){
                publishMessage(wxUser, params, tuCaoTemplate, wxProperties.getTucaoImg(), "吐槽");
            }
            return JsonResult.fillResultString(200, "success", null);
        }else {
            return JsonResult.fillResultString(-1, "other problem", null);
        }
    }

    /**
     * quiet push
     * @param articleId
     * @return
     */
    public String pushQuietMessage(int articleId){
        Map<String, Object> article = articleMapper.queryArticleDetail(articleId);
        if (MapUtils.isNotEmpty(article)){
            String wxId = MapUtils.getString(article, StringConstant.ARTICLE_AUTHOR_ID);
            Map<String, Object> wxUser = organizeMapper.findWxUserById(wxId);
            if (MapUtils.isNotEmpty(wxUser)){
                int type = MapUtils.getIntValue(article, StringConstant.ARTICLE_TYPE);
                if (0 == type){
                    //推送
                    publishMessage(wxUser, article, qiSiTemplate, wxProperties.getMessageImg(), "想法");
                }else if (1 == type){
                    publishMessage(wxUser, article, tuCaoTemplate, wxProperties.getTucaoImg(), "吐槽");
                }
            }else {
                return JsonResult.fillResultString(-1, "no author", null);
            }
        }else {
            return JsonResult.fillResultString(-1, "no article", null);
        }

        return JsonResult.fillResultString(200, "success", null);
    }


    private void publishMessage(Map<String, Object> wxUser, Map<String, Object> article, String template, String messageImgId, String xfOrTc) {


        //get depart
        String wxUserId = MapUtils.getString(wxUser, "user_id");
        String wxUserName = MapUtils.getString(wxUser, "user_name");
        String articleId = MapUtils.getString(article, StringConstant.ARTICLE_ID);

        List<Map<String, Object>> departs = organizeMapper.findDepartByWxUserId(wxUserId);
        if (CollectionUtils.isNotEmpty(departs)){
            Map<String, Object> depart = departs.get(0);
            String departName = MapUtils.getString(depart, StringConstant.DEPART_NAME);


            String msgTitle = departName + " " + wxUserName + " 的" + xfOrTc;
            String digest = MapUtils.getString(article, StringConstant.ARTICLE_TITLE);
            String departAndUser = departName + " " + wxUserName;
            String receiveUserId = "@all";
            String content = template.replace("{{departAndUser}}", departAndUser).replace("{{topic}}", "《" + digest + "》");
            String contentSourceUrl = wxProperties.getAccess() + "/oauth/getOauth?articleId=" + articleId;

            messageService.sendNewsMessage(receiveUserId, digest, content, msgTitle, contentSourceUrl, messageImgId);
        }
    }

    /**
     * 显示文章列表
     *
     * @param pageSize
     * @param pageNum
     * @param type
     * @return
     */
    public String showArticleList(int pageSize, int pageNum, int type, String userId) {
        List<Map<String, Object>> articles;
        //总数
        int totalNum;
        if (type < 0){
            articles = articleMapper.queryAllArticleList(pageSize * ( pageNum- 1), pageSize);
            totalNum = articleMapper.queryTotalArticle();
        }else {
            articles = articleMapper.queryArticleListByType( pageSize* (pageNum - 1), pageSize,  type);
            totalNum = articleMapper.queryTotalArticleInType(type);
        }
        //如果结果不为空，查找图片
        if (CollectionUtils.isNotEmpty(articles)){
            for (Map<String, Object> article : articles){
                article.put(StringConstant.TOTAL_NUM, totalNum);
                int articleId = MapUtils.getIntValue(article, StringConstant.ARTICLE_ID);
                List<Map<String, String>> imgs = this.getArticleImgs(articleId);
                if (CollectionUtils.isNotEmpty(imgs)){
                    article.put(StringConstant.ARTICLE_MAIN_IMG, imgAccess + MapUtils.getString(imgs.get(0), StringConstant.IMAGE_UID_NAME));
                }

                String wxId = MapUtils.getString(article, StringConstant.ARTICLE_AUTHOR_ID);
                //depart
                List<Map<String, Object>> departs = organizeMapper.findDepartByWxUserId(wxId);
                if (CollectionUtils.isNotEmpty(departs)){
                    article.put("depart", departs.get(0));
                }
                //userinfo
                Map<String, Object> userInfo = organizeMapper.findWxUserById(wxId);
                article.put("userInfo", userInfo);

                // 判断是否已读
                List<String> readers = articleMapper.queryReaders(articleId);
                article.put("read", readers.contains(userId) ? 1 : 0);
            }
        }
        return JsonResult.fillResultString(200, "success", articles);
    }


    /**
     * 根据文章id查找图片
     *
     * @param articleId
     * @return
     */
    private List<Map<String, String>> getArticleImgs(int articleId){
        return articleMapper.queryImgsByArticleId(articleId);
    }

    /**
     * 显示文章detail
     *
     * @param id
     * @return
     */
    public String showArticleDetail(int id) {
        Map<String, Object> article = articleMapper.queryArticleDetail(id);
        //查看是否有图片，如果有， 取出图片
        List<Map<String, String>> imgs = this.getArticleImgs(MapUtils.getIntValue(article, StringConstant.ARTICLE_ID));
        if (CollectionUtils.isNotEmpty(imgs)){
            for (Map<String, String> img : imgs){
                img.put("imgAddress", imgAccess + MapUtils.getString(img, StringConstant.IMAGE_UID_NAME));
            }
        }
        article.put(StringConstant.IMAGES, imgs);
        String wxId = MapUtils.getString(article, StringConstant.ARTICLE_AUTHOR_ID);
        //depart
        List<Map<String, Object>> departs = organizeMapper.findDepartByWxUserId(wxId);
        if (CollectionUtils.isNotEmpty(departs)){
            article.put("depart", departs.get(0));
        }
        //userinfo
        Map<String, Object> userInfo = organizeMapper.findWxUserById(wxId);
        article.put("userInfo", userInfo);
        return JsonResult.fillResultString(200, "success", article);
    }

    /**
     * close article
     *
     * @param articleId
     * @param wxId
     * @return
     */
    public String closeArticle(int articleId, String wxId) {
        Map<String, Object> article = articleMapper.queryArticleDetail(articleId);
        // exists article ?
        if (MapUtils.isEmpty(article)){
            return JsonResult.fillResultString(-1, "wrong articleid", null);
        }
        // own article ?
        if (!wxId.equals(MapUtils.getString(article, StringConstant.ARTICLE_AUTHOR_ID))){
            return JsonResult.fillResultString(-1, "wrong author", null);
        }
        //already?
        if (1 == MapUtils.getIntValue(article, StringConstant.ARTICLE_CLOSE)){
            return JsonResult.fillResultString(200, "success", null);
        }
        //执行关贴操作
        int res = articleMapper.closeArticle(articleId);
        if (res == 1){
            return JsonResult.fillResultString(200, "success", null);
        }else {
            return JsonResult.fillResultString(-1, "fail", null);
        }
    }

    /**
     * showArticleListByDepartAndMonth
     *
     * @param pageSize
     * @param pageNum
     * @param type
     * @param departId
     * @param yearMonth
     * @return
     */
    public String showArticleListByDepartAndMonth(int pageNum, int pageSize, int type, int departId, String yearMonth) {
        String pattern = "\\d{4}-\\d{2}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(yearMonth);
        if (!m.matches()){
            return JsonResult.fillResultString(-1, "wrong yearMonth", null);
        }
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(yearMonth.indexOf("-") + 1, yearMonth.length()));
        if (year > 9999 || year < 2000 || month > 12 || month < 1){
            return JsonResult.fillResultString(-1, "wrong yearMonth", null);
        }
        //部门是否有？
        Map<String, Object> depart = organizeMapper.findDepartById(departId);
        if (MapUtils.isEmpty(depart)){
            return JsonResult.fillResultString(-1, "not in depart", null);
        }

        LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endTime = startTime.plusMonths(1).minusSeconds(1);

        int totalNum = articleMapper.queryArticleCountByDepart(departId, type, startTime, endTime);
        List<Map<String, Object>> articles = articleMapper.queryArticleByDepart(departId, type, startTime, endTime,
                (pageNum - 1) * pageSize, pageSize);
        //如果结果不为空，查找图片
        if (CollectionUtils.isNotEmpty(articles)){
            for (Map<String, Object> article : articles){
                article.put(StringConstant.TOTAL_NUM, totalNum);
                int articleId = MapUtils.getIntValue(article, StringConstant.ARTICLE_ID);
                List<Map<String, String>> imgs = this.getArticleImgs(articleId);
                if (CollectionUtils.isNotEmpty(imgs)){
                    article.put(StringConstant.ARTICLE_MAIN_IMG, imgAccess + MapUtils.getString(imgs.get(0), StringConstant.IMAGE_UID_NAME));
                }
            }
        }
        return JsonResult.fillResultString(200, "success", articles);
    }

    /**
     * get read num
     * @param id
     * @return
     */
    public String getReadNum(int id) {
        return JsonResult.fillResultString(200, "success", articleMapper.queryReadCount(id));
    }

    /**
     * set reader
     *
     * @param articleId
     * @param wxId
     * @return
     */
    public String setReader(int articleId, String wxId){
        Map<String, Object> article = articleMapper.queryArticleDetail(articleId);
        if (MapUtils.isNotEmpty(article)){
            Map<String, Object> wxUser = organizeMapper.findWxUserById(wxId);
            if (MapUtils.isNotEmpty(wxUser)){
                Map<String, Object> record = articleMapper.queryArticleReadRecord(articleId, wxId);
                if (MapUtils.isEmpty(record)){
                    //insert record
                    int result = articleMapper.insertReadRecord(articleId, wxId);
                    if (result > 0){
                        return JsonResult.fillResultString(200, "success", null);
                    }else{
                        return JsonResult.fillResultString(-1, "error", null);
                    }
                }else{
                    return JsonResult.fillResultString(200, "alread in", null);
                }
            }else{
                return JsonResult.fillResultString(-1, "wrong wxUser", null);
            }
        }else {
            return JsonResult.fillResultString(-1, "wrong article", null);
        }
    }

    /**
     * 显示阅读者详情
     *
     * @param articleId
     * @return
     */
    public String getReaderDetail(int articleId) {
        List<Map<String, Object>> readers = organizeMapper.findReadersByArticleId(articleId);
        if (CollectionUtils.isNotEmpty(readers)){
            List<String> names = new ArrayList<>();
            for (Map<String, Object> reader : readers){
                String wxId = MapUtils.getString(reader, "user_id");
                List<Map<String, Object>> departs = organizeMapper.findDepartByWxUserId(wxId);
                if (CollectionUtils.isNotEmpty(departs)){
                    names.add(MapUtils.getString(departs.get(0), "departName", "无部门") + " "
                            +  MapUtils.getString(reader, "user_name", ""));
                }else{
                    names.add("无部门 " + MapUtils.getString(reader, "user_name", ""));
                }
            }
            return JsonResult.fillResultString(200, "success", names);
        }else {
            return JsonResult.fillResultString(-1, "wrong", null);
        }

    }
}

