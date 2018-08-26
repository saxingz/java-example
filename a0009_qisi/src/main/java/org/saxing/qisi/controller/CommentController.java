package org.saxing.qisi.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.saxing.qisi.bean.JsonResult;
import org.saxing.qisi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment controller
 *
 * Created by saxing on 2018/5/8.
 */
@Slf4j
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 发表评论
     *
     * @param articleId 文章id
     * @param content 最多100字
     * @param wxId 评论者微信id
     * @return
     */
    @ApiOperation(value = "发表评论", notes = "?articleId=27&wxId=LiuHan&content=abc")
    @GetMapping("/publish.action")
    public String publishComment(@RequestParam(value = "articleId") int articleId,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "wxId") String wxId){
        if (StringUtils.isEmpty(wxId) || articleId < 1 || StringUtils.isEmpty(content) || content.length() > 100){
            return JsonResult.fillResultString(-1, "wrong param", null);
        }
        return commentService.publish(wxId, articleId,  content);
    }

    /**
     * 评论点赞
     *
     * @param commentId 评论id
     * @param wxId 微信id
     * @return
     */
    @ApiOperation(value = "评论点赞", notes = "?commentId=27&wxId=LiuHan")
    @GetMapping("/thumbComment.action")
    public String thumbComment(@RequestParam(value = "commentId") int commentId,
                               @RequestParam(value = "wxId") String wxId){
        if (commentId < 1 || StringUtils.isEmpty(wxId)){
            return JsonResult.fillResultString(-1, "wrong param", null);
        }
        return commentService.thumbComment(commentId, wxId);
    }

    /**
     * 显示评论，首先按点赞数，然后按发表时间排序，且显示自己是否已点赞
     *
     * @param articleId 文章id
     * @param wxId 微信id
     * @return
     */
    @ApiOperation(value = "显示评论", notes = "首先按点赞数，然后按发表时间排序，且显示自己是否已点赞  ?articleId=27&wxId=LiuHan")
    @GetMapping("/showComment.action")
    public String showComment(@RequestParam(value = "articleId") int articleId,
                              @RequestParam(value = "wxId") String wxId){
        if (articleId < 1 || StringUtils.isEmpty(wxId)){
            return JsonResult.fillResultString(-1, "wrong param", null);
        }
        return commentService.showComment(articleId, wxId);
    }

}
