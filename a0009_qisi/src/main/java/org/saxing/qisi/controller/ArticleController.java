package org.saxing.qisi.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.saxing.qisi.bean.JsonResult;
import org.saxing.qisi.service.ArticleService;
import org.saxing.qisi.utils.ImageUtils;
import org.saxing.qisi.utils.SaIdUtils;
import org.saxing.qisi.utils.SaParamsUtils;
import org.saxing.qisi.utils.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * article
 *
 * Created by saxing on 2018/5/6.
 */
@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Value("${img.saveLocation}")
    private String imgSaveLocation;
    @Value("${wx.access}")
    private String accessUrl;
    @Value("${img.imgAccess}")
    private String imgAccess;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 发布文章
     * @param params
     * @return
     */
    @ApiOperation(value = "发布文章",
            notes = "{" +
                    "    \"wxUserId\": \"LiuHan\"," +
                    "    \"content\": \"this is a content\"," +
                    "    \"title\": \"this is a title\"," +
                    "    \"advice\": \"this is a advice\"," +
                    "    \"type\": 0," +
                    "    \"images\": [" +
                    "        {" +
                    "            \"originName\": \"img1.jpg\"," +
                    "            \"uidName\": \"2321321432.jpg\"," +
                    "            \"index\": 0" +
                    "        }," +
                    "        {" +
                    "            \"originName\": \"img1.png\"," +
                    "            \"uidName\": \"321432.png\"," +
                    "            \"index\": 1" +
                    "        }," +
                    "        {" +
                    "            \"originName\": \"img1.jpeg\"," +
                    "            \"uidName\": \"543543543.jpeg\"," +
                    "            \"index\": 2" +
                    "        }" +
                    "    ]" +
                    "}")
    @PostMapping(value = "/uploadArticle.action")
    @ResponseBody
    public String uploadArticle(@RequestBody Map<String, Object> params){
        if (!SaParamsUtils.paramIsNotNull(params,
                new String[]{StringConstant.WX_USER_ID, StringConstant.STRING},
                new String[]{StringConstant.ARTICLE_TITLE, StringConstant.STRING},
                new String[]{StringConstant.ARTICLE_CONTENT, StringConstant.STRING},
                new String[]{StringConstant.ARTICLE_ADVICE, StringConstant.STRING},
                new String[]{StringConstant.ARTICLE_TYPE, StringConstant.STRING})) {
            return JsonResult.fillResultString(-1, "wrong params", null );
        }
        return articleService.publishArticle(params);
    }

    /**
     * 文章关贴（不再评论）
     *
     * @param articleId 文章id
     * @param wxId 微信id
     * @return
     */
    @ApiOperation(value = "文章关贴（不再评论）", notes = "?articleId=3&wxId=XX")
    @RequestMapping(value = "/closeArticle.action", method = RequestMethod.GET)
    @ResponseBody
    public String closeArticle(@RequestParam(value = "articleId") int articleId,
                               @RequestParam(value = "wxId") String wxId){
        if (articleId < 1 || StringUtils.isEmpty(wxId)){
            return JsonResult.fillResultString(-1, "wrong param", null);
        }
        return articleService.closeArticle(articleId, wxId);
    }

    /**
     * 显示文章列表
     * @param pageSize 每页数量
     * @param pageNum 页码
     * @param type 0=奇思 1=吐槽 -1=全部
     * @return
     */
    @ApiOperation(value = "显示文章列表", notes = "?pageSize=3&pageNum=1&type=0&wxId=LiuHan")
    @GetMapping("/showArticleList.action")
    @ResponseBody
    public String showArticleList(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "type", defaultValue = "0") int type,
                                  @RequestParam(value = "wxId", defaultValue = "") String wxId){
        //检验参数
        pageSize = pageSize < 1 ? 10 : pageSize;
        pageNum = pageNum < 1 ? 1 : pageNum;
        return articleService.showArticleList(pageSize, pageNum, type, wxId);
    }

    /**
     * 按部门 月 显示文章列表
     * @param pageSize 每页数量
     * @param pageNum 页码
     * @param type 0=奇思 1=吐槽 -1=全部
     * @return
     */
    @ApiOperation(value = "按部门 月 显示文章列表", notes = "?pageSize=3&pageNum=1&type=0")
    @GetMapping("/showArticleListByDepartAndMonth.action")
    @ResponseBody
    public String showArticleListByDepartAndMonth(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                  @RequestParam(value = "type", defaultValue = "0") int type,
                                                  @RequestParam(value = "departId") int departId,
                                                  @RequestParam(value = "yearMonth") String yearMonth){
        if (departId < 0 || StringUtils.isEmpty(yearMonth)){
            return JsonResult.fillResultString(-1, "wrong param", null);
        }
        //检验参数
        pageSize = pageSize < 1 ? 10 : pageSize;
        pageNum = pageNum < 1 ? 1 : pageNum;
        return articleService.showArticleListByDepartAndMonth(pageSize, pageNum, type, departId, yearMonth);
    }

    /**
     * 上传图片
     *
     * @param multipartFile 上传文件
     * @return
     */
    @ApiOperation(value = "上传图片",
            notes = "")
    @PutMapping("/imgUpload.action")
    @ResponseBody
    public String uploadImg(@RequestParam(value = "multipartFile") MultipartFile multipartFile){
        if (multipartFile == null || multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())){
            return JsonResult.fillResultString(-1, "wrong params", null);
        }
        String contentType = multipartFile.getContentType();
        if (StringUtils.isEmpty(contentType)){
            return JsonResult.fillResultString(-1, "wrong params", null);
        }
        if (!contentType.toLowerCase().contains("jpg") && !contentType.toLowerCase().contains("png")
                && !contentType.toLowerCase().contains("jpeg") && !contentType.toLowerCase().contains("bmp")){
            return JsonResult.fillResultString(-1, "wrong params", null);
        }
        String fileOriginName = multipartFile.getOriginalFilename();
        String suffix = ImageUtils.getSuffix(fileOriginName);
        log.info("上传图片， name={}, type={}", fileOriginName, contentType);
        //处理图片
        String filePath = imgSaveLocation;
        String fileUidName = SaIdUtils.getSomeIds() + "." + suffix;
        try {
            ImageUtils.saveImg(multipartFile, filePath, fileUidName);
            ImageUtils.compressPic(filePath + File.separator + fileUidName);
        } catch (IOException e) {
            log.error("上传图片失败， name={}, type={}", fileOriginName, contentType);
            return JsonResult.fillResultString(-1, "upload failed", null);
        }
        return JsonResult.fillResultString(200, "success",
                new HashMap<String, String>(){{
                    put("picPath", imgAccess + fileUidName);
                    put("picName", fileOriginName);
                    put(StringConstant.IMAGE_UID_NAME, fileUidName);
        }});
    }

    /**
     * 显示文章详情
     *
     * @param id 文章id
     * @return
     */
    @ApiOperation(value = "显示文章详情", notes = "?id=3")
    @GetMapping("/showDetail.action")
    @ResponseBody
    public String showDetail(@RequestParam(value = "id") int id){
        if (id < 1){
            return JsonResult.fillResultString(-1, "wrong id", null);
        }
        return articleService.showArticleDetail(id);
    }


    /**
     * quietPush
     *
     * @param articleId articleId
     * @return
     */
    @ApiOperation(value = "quietPush", notes = "?articleId=3")
    @GetMapping("/quietPush.action")
    @ResponseBody
    public String quietPush(@RequestParam(value = "articleId") int articleId){
        return articleService.pushQuietMessage(articleId);
    }

    /**
     * 获取阅读数
     *
     * @param id id
     * @return
     */
    @ApiOperation(value = "获取阅读数", notes = "?id=3")
    @GetMapping("/getReadNum.action")
    @ResponseBody
    public String getReadNum(@RequestParam(value = "id") int id){
        return articleService.getReadNum(id);
    }

    /**
     * 增加阅读者
     *
     * @param articleId articleId
     * @param wxId wxId
     * @return
     */
    @ApiOperation(value = "获取阅读数", notes = "?articleId=3&wxId=LiuHan")
    @GetMapping("/setReader.action")
    @ResponseBody
    public String setReader(@RequestParam(value = "articleId") int articleId, @RequestParam(value = "wxId") String wxId){
        return articleService.setReader(articleId, wxId);
    }

    /**
     * 查看阅读者详情
     * @param articleId
     *
     * @return
     */
    @GetMapping("/getReaderDetail.action")
    @ApiOperation(value = "获取阅读者详情", notes = "?articleId=3")
    @ResponseBody
    public String getReaderDetail(@RequestParam(value = "articleId") int articleId){
        return articleService.getReaderDetail(articleId);
    }


}
