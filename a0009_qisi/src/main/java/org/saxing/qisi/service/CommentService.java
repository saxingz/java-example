package org.saxing.qisi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.saxing.qisi.bean.JsonResult;
import org.saxing.qisi.config.WxProperties;
import org.saxing.qisi.dao.mapper.ArticleMapper;
import org.saxing.qisi.dao.mapper.CommentMapper;
import org.saxing.qisi.dao.mapper.OrganizeMapper;
import org.saxing.qisi.utils.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * commment service
 *
 * Created by saxing on 2018/5/8.
 */
@Service
@Slf4j
public class CommentService {

    private final OrganizeMapper organizeMapper;
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;
    private final MessageService messageService;
    private final WxProperties wxProperties;


    @Autowired
    public CommentService(OrganizeMapper organizeMapper, ArticleMapper articleMapper, CommentMapper commentMapper, MessageService messageService, WxProperties wxProperties) {
        this.organizeMapper = organizeMapper;
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
        this.messageService = messageService;
        this.wxProperties = wxProperties;
    }


    /**
     * 发表评论
     *
     * @param wxId
     * @param articleId
     * @param content
     * @return
     */
    public String publish(String wxId, int articleId, String content) {
        // 检测是系统里的人
        Map<String, Object> wxUser = organizeMapper.findWxUserById(wxId);
        if (MapUtils.isEmpty(wxUser)){
            return JsonResult.fillResultString(-1, "not in person", null);
        }
        // 检测文章是否关闭
        Map<String, Object> article = articleMapper.queryArticleDetail(articleId);
        if (MapUtils.isEmpty(article) || MapUtils.getIntValue(article, StringConstant.ARTICLE_CLOSE) == 1){
            return JsonResult.fillResultString(-2, "article closed", null);
        }
        Map<String, Object> params = new HashMap<>();
        params.put(StringConstant.COMMENT_ARTICLE_ID, articleId);
        params.put(StringConstant.COMMENT_AUTHOR_WX_ID, wxId);
        params.put(StringConstant.COMMENT_CONTENT, content);
        int result = commentMapper.insertComment(params);
        //发表完后，通知所有评论过 和 点赞过的人
        notifyCommenterAndThumber(wxUser, article, content);
        if (result == 1){
            return JsonResult.fillResultString(200, "success", null);
        }else {
            log.error("发表评论错误");
            return JsonResult.fillResultString(-1, "error", null);
        }


    }

    /**
     * 发表完后，通知所有评论过 和 点赞过的人
     *
     * @param wxUser
     * @param article
     * @param commentContent
     */
    private void notifyCommenterAndThumber(Map<String, Object> wxUser, Map<String, Object> article, String commentContent) {
        String wxUserId = MapUtils.getString(wxUser, "user_id");
        String wxUserName = MapUtils.getString(wxUser, "user_name");
        String articleTitle = MapUtils.getString(article, StringConstant.ARTICLE_TITLE);
        String template = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 style=\"display: inline-block\"><span style=\"color: red;\">{{departAndUser}}</span>&nbsp;&nbsp;回复了&nbsp;&nbsp;</h1>\n" +
                "<h1 style=\"display: inline-block\">{{articleTitle}}</h1>\n" +
                "<h1>内容是：</h1>\n" +
                "<h1>{{commentContent}}</h1><br>\n" +
                "<h1>点击阅读原文围观讨论哦~</h1>\n" +
                "</body>\n" +
                "</html>";


        int articleId = MapUtils.getIntValue(article, StringConstant.ARTICLE_ID);
        String articleAuthor = MapUtils.getString(article, StringConstant.ARTICLE_AUTHOR_ID);
        // 这里最多只能发1000人
        Set<String> allArticleUserId = new HashSet<>();
        allArticleUserId.add(articleAuthor);
        // add author first
        // 找出所有评论的人
        List<Map<String, Object>> comments = commentMapper.findCommentsOrderByThumbNum(articleId);
        if (CollectionUtils.isNotEmpty(comments)){
            for (Map<String, Object> comment : comments){
                allArticleUserId.add(MapUtils.getString(comment, StringConstant.COMMENT_AUTHOR_WX_ID));
                int commentId = MapUtils.getIntValue(comment, StringConstant.COMMENT_ID);
                List<String> thumbers = commentMapper.findThumber(commentId);
                allArticleUserId.addAll(thumbers);
            }
        }else{
            return;
        }

        List<Map<String, Object>> departs = organizeMapper.findDepartByWxUserId(wxUserId);
        if (CollectionUtils.isNotEmpty(departs)){
            Map<String, Object> depart = departs.get(0);
            String departName = MapUtils.getString(depart, StringConstant.DEPART_NAME);

            StringBuilder sb = new StringBuilder();
            for (String wi : allArticleUserId){
                sb.append(wi).append("|");
            }
            String tempSb = sb.toString();
            String receiveUserId = tempSb.substring(0, tempSb.length() - 1);

            String msgTitle = departName + " " + wxUserName + " 回复了";
            String departAndUser = departName + " " + wxUserName;
            String content = template.replace("{{departAndUser}}", departAndUser)
                    .replace("{{articleTitle}}", "《" + articleTitle + "》").replace("{{commentContent}}", commentContent);
            String contentSourceUrl = wxProperties.getAccess() + "/oauth/getOauth?articleId=" + articleId;
            messageService.sendNewsMessage(receiveUserId, articleTitle, content, msgTitle, contentSourceUrl, wxProperties.getDiscussImg());
        }



    }



    /**
     * thumb comment
     *
     * @param commentId
     * @param wxId
     * @return
     */
    @Transactional
    public String thumbComment(int commentId, String wxId) {
        // 检测是系统里的人
        Map<String, Object> wxUser = organizeMapper.findWxUserById(wxId);
        if (MapUtils.isEmpty(wxUser)){
            return JsonResult.fillResultString(-1, "not in person", null);
        }
        //get comment
        Map<String, Object> comment = commentMapper.findCommentById(commentId);
        if (MapUtils.isEmpty(comment)){
            return JsonResult.fillResultString(-1, "comment not exists", null);
        }
        //thumbnum++
        int thumbNum = MapUtils.getIntValue(comment, StringConstant.COMMENT_THUMB_NUM);
        comment.put(StringConstant.COMMENT_THUMB_NUM, ++thumbNum);
        //update
        comment.remove(StringConstant.CREATE_TIME);
        comment.remove(StringConstant.UPDATE_TIME);
        commentMapper.updateComment(comment);

        //record commenter
        commentMapper.saveThumber(commentId, wxId);
        return JsonResult.fillResultString(200, "success", null);
    }

    /**
     * show comment
     *
     * @param articleId
     * @param wxId
     * @return
     */
    public String showComment(int articleId, String wxId) {
        // 检测是系统里的人
        Map<String, Object> wxUser = organizeMapper.findWxUserById(wxId);
        if (MapUtils.isEmpty(wxUser)){
            return JsonResult.fillResultString(-1, "not in person", null);
        }
        // find all comment
        List<Map<String, Object>> allComments = commentMapper.findCommentsOrderByTime(articleId);
        if (CollectionUtils.isEmpty(allComments)){
            return JsonResult.fillResultString(200, "success no content", null);
        }
        //verify self
        for (Map<String, Object> comment : allComments){
            List<String> thumber = commentMapper.findThumber(MapUtils.getIntValue(comment, StringConstant.COMMENT_ID));
            if (CollectionUtils.isNotEmpty(thumber)){
                if (thumber.contains(wxId)){
                    comment.put("thumbed", 1);
                }else {
                    comment.put("thumbed", 0);
                }
            }else {
                comment.put("thumbed", 0);
            }

            // find avatar
            String commentUserId = MapUtils.getString(comment, StringConstant.COMMENT_AUTHOR_WX_ID);
            if (StringUtils.isNotEmpty(commentUserId)){
                Map<String, Object> commenter = organizeMapper.findWxUserById(commentUserId);

                List<Map<String, Object>> departs = organizeMapper.findDepartByWxUserId(commentUserId);
                if (CollectionUtils.isNotEmpty(departs)){
                    commenter.putAll(departs.get(0));
                }
                comment.put("commenter", commenter);
            }
        }

        return JsonResult.fillResultString(200, "success", allComments);
    }


    public static void main(String[] args) {
        String s = "SELECT * from (" +
                " (" +
                "  SELECT IFNULL(g.goodNum, 0) as goodNum, IFNULL(g.departId, b.departId) as departId, " +
                "  IFNULL(g.departName, b.departName) as departName, IFNULL(b.badNum, 0) as badNum" +
                "  FROM " +
                " (" +
                " SELECT count(1) as goodNum, d.depart_id as departId, d.depart_name as departName" +
                "  FROM article a, depart_user du, depart d " +
                "  WHERE" +
                "  a.author_wxid = du.user_id" +
                "  AND du.depart_id = d.depart_id" +
                "  AND a.type=0" +
                "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
                "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
                "  GROUP BY d.depart_id" +
                " ) g" +
                " RIGHT JOIN " +
                " (" +
                " SELECT count(1) as badNum, d.depart_id as departId, d.depart_name as departName" +
                "  FROM article a, depart_user du, depart d " +
                "  WHERE" +
                "  a.author_wxid = du.user_id" +
                "  AND du.depart_id = d.depart_id" +
                "  AND a.type=1" +
                "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
                "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
                "  GROUP BY d.depart_id" +
                " " +
                " ) b ON b.departId = g.departId" +
                " ORDER BY g.goodNum ASC" +
                " )" +
                " UNION" +
                " (" +
                "  SELECT IFNULL(g.goodNum, 0) as goodNum, IFNULL(g.departId, b.departId) as departId, " +
                "  IFNULL(g.departName, b.departName) as departName, IFNULL(b.badNum, 0) as badNum" +
                "  FROM " +
                " (" +
                " " +
                " SELECT count(1) as goodNum, d.depart_id as departId, d.depart_name as departName" +
                "  FROM article a, depart_user du, depart d " +
                "  WHERE" +
                "  a.author_wxid = du.user_id" +
                "  AND du.depart_id = d.depart_id" +
                "  AND a.type=0" +
                "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
                "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
                "  GROUP BY d.depart_id" +
                " ) g" +
                " LEFT JOIN " +
                " (" +
                " SELECT count(1) as badNum, d.depart_id as departId, d.depart_name as departName" +
                "  FROM article a, depart_user du, depart d " +
                "  WHERE" +
                "  a.author_wxid = du.user_id" +
                "  AND du.depart_id = d.depart_id" +
                "  AND a.type=1" +
                "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
                "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
                "  GROUP BY d.depart_id" +
                " ) b ON b.departId = g.departId" +
                " )" +
                " ) res" +
                " ORDER BY res.goodNum DESC";
        System.out.println(s);
    }
}

















