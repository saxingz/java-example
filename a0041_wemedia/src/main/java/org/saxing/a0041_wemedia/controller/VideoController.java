package org.saxing.a0041_wemedia.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.saxing.a0041_wemedia.logic.IVideoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 原始视频表 前端控制器
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/v1/video")
@Api(tags = "原始视频接口")
public class VideoController {

    @Autowired
    private IVideoLogic videoLogic;

    /**
     * 解析youtube json
     * @param json json
     * @return res
     */
    @ApiOperation("解析youtube json")
    @ApiImplicitParam(name = "json", value = "youtube json内容")
    @PostMapping("/parse-json")
    public String parseYouTubeJson(String json){
        return videoLogic.parseYoutubeJson(json);
    }



}

