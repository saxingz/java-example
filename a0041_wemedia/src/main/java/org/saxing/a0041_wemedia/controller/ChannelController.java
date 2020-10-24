package org.saxing.a0041_wemedia.controller;


import cn.hutool.core.date.DateBetween;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.saxing.a0041_wemedia.domain.entity.ChannelDO;
import org.saxing.a0041_wemedia.logic.IChannelLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * <p>
 * 频道名称 前端控制器
 * </p>
 *
 * @author saxing
 * @since 2020-10-22
 */
@RestController
@RequestMapping("/v1/channel")
@Api(tags = "频道接口")
@Validated
public class ChannelController {

    @Autowired
    private IChannelLogic channelLogic;

    private static DateTime tagTime = DateTime.now();

    /**
     * 扫描频道
     * @param startDateStr 开始日期
     * @param endDateStr 结束日期
     * @param channelId 频道id
     * @return res
     *
     * //        DateTime startDate = DateUtil.parse("2012-01-01 00:00:00");
     * //        DateTime endDate = DateUtil.parse("2014-01-01 00:00:00");
     *             // "UCZYTClx2T1of7BRZ86-8fow"
     */
    @ApiOperation("扫描频道")
    @PostMapping("/scan")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDateStr", value = "开始时间", defaultValue = "2012-01-01 00:00:00"),
            @ApiImplicitParam(name = "endDateStr", value = "结束时间", defaultValue = "2014-01-01 00:00:00"),
            @ApiImplicitParam(name = "channelId", value = "频道ID")
    })
    public String scanChannel(String startDateStr, String endDateStr, String channelId) throws Exception {
        // 设置无法重复点击，10s一次
        DateTime now = DateTime.now();
        DateBetween between = now.between(tagTime);
        long diffSeconds = between.between(DateUnit.SECOND);
        if (diffSeconds < 10){
            return "每10s可点击一次";
        }
        DateTime startDate = DateUtil.parse(startDateStr);
        DateTime endDate = DateUtil.parse(endDateStr);
        new Thread(() -> channelLogic.scanChannel(startDate, endDate, channelId)).start();
        tagTime = DateTime.now();
        return "success";
    }

    /**
     * 查看频道列表
     *
     * @param channelDO 频道条件
     * @param page 页码
     * @param pageNum  一页数量
     * @return res
     */
    @ApiOperation("查看频道列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "pageNum", value = "每页条数", defaultValue = "1")
    })
    @PostMapping("/list")
    public Page<ChannelDO> list(@RequestBody ChannelDO channelDO,
                                @RequestParam @Min(value = 1, message = "页码不得少于1") Integer page,
                                @RequestParam @Min(value = 1, message = "每页条数不得少于1")
                                @Max(value = 100, message = "每页条数不大于100") Integer pageNum){
        LambdaQueryChainWrapper<ChannelDO> queryChainWrapper = channelLogic.lambdaQuery();
        queryChainWrapper.like(StringUtils.isNotEmpty(channelDO.getChannelTitle()),
                ChannelDO::getChannelTitle, channelDO.getChannelTitle());
        queryChainWrapper.like(StringUtils.isNotEmpty(channelDO.getChannelId()),
                ChannelDO::getChannelId, channelDO.getChannelId());
        return channelLogic.page(new Page<>(page, pageNum), queryChainWrapper)
                .addOrder(OrderItem.desc(TableInfoHelper.getTableInfo(ChannelDO.class).getKeyProperty()));
    }


    /**
     * 增加或修改频道
     * @param channelDO 参数
     * @return res
     */
    @ApiOperation("增加或修改频道")
    @PostMapping("/add-or-update")
    public Boolean add(@RequestBody ChannelDO channelDO){
        return channelLogic.saveOrUpdate(channelDO);
    }

}

