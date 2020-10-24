package org.saxing.a0041_wemedia.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import org.saxing.a0041_wemedia.domain.entity.ChannelDO;
import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.logic.IVideoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.List;

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
@Validated
@CommonsLog
public class VideoController {

    @Autowired
    private IVideoLogic videoLogic;


    /**
     * 查看原始视频
     */
    @ApiOperation("查看原始视频")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "pageNum", value = "每页条数", defaultValue = "1")
    })
    @PostMapping("/list")
    public Page<VideoDO> listVideos(@RequestBody VideoDO video,
                                    @RequestParam @Min(value = 1, message = "页码不得少于1") Integer page,
                                    @RequestParam @Min(value = 1, message = "每页条数不得少于1")
                                    @Max(value = 100, message = "每页条数不大于100") Integer pageNum){
        LambdaQueryWrapper<VideoDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(video.getChannelId()), VideoDO::getChannelId, video.getChannelId());
        queryWrapper.like(StringUtils.isNotBlank(video.getChannelTitle()), VideoDO::getChannelTitle, video.getChannelTitle());
        queryWrapper.like(StringUtils.isNotBlank(video.getVideoId()), VideoDO::getVideoId, video.getVideoId());
        queryWrapper.like(StringUtils.isNotBlank(video.getVideoTitle()), VideoDO::getVideoTitle, video.getVideoTitle());
        queryWrapper.like(StringUtils.isNotBlank(video.getDescription()), VideoDO::getDescription, video.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(video.getDownloadedUrl()), VideoDO::getDownloadedUrl, video.getDownloadedUrl());
        return videoLogic.page(new Page<>(page, pageNum), queryWrapper)
                .addOrder(OrderItem.desc(TableInfoHelper.getTableInfo(VideoDO.class).getKeyProperty()));
    }

    /**
     * 修改原始视频
     * @param video video
     * @return res
     */
    @ApiOperation("修改原始视频")
    @PostMapping("/update")
    public Boolean updateVideo(@RequestBody VideoDO video){
        return videoLogic.updateById(video);
    }

    /**
     * 下载视频
     * @return
     */
    @ApiOperation("下载视频")
    @ApiImplicitParam(name = "id", value = "视频id")
    @PostMapping("/download")
    public Boolean downloadVideo(@RequestParam Long id) throws Exception {
        new Thread(() -> {
            try {
                videoLogic.downloadVideo(id);
            } catch (IOException e) {
                log.error(e.toString());
                e.printStackTrace();
            }
        }).start();
        return true;
    }

    /**
     * 重建视频
     * @return
     */
    @ApiOperation("重建视频")
    @ApiImplicitParam(name = "id", value = "重建视频")
    @PostMapping("/rebuild")
    public Boolean rebuildVideo(@RequestParam Long id) throws Exception {
        new Thread(() -> {
            try {
                videoLogic.rebuild(id);
            } catch (IOException e) {
                log.error(e.toString());
                e.printStackTrace();
            }
        }).start();
        return true;
    }

}

