package org.saxing.a0041_wemedia.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.saxing.a0041_wemedia.domain.entity.TransferDO;
import org.saxing.a0041_wemedia.logic.ITransferLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * <p>
 * 搬运表 前端控制器
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/transfer-do")
@Api(tags = "搬运视频接口")
@Validated
public class TransferController {

    @Autowired
    private ITransferLogic transferLogic;

    /**
     * 查看搬运视频
     */
    @ApiOperation("查看搬运视频")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "pageNum", value = "每页条数", defaultValue = "1")
    })
    @PostMapping("/list-videos")
    public Page<TransferDO> listVideos(@RequestBody TransferDO transfer,
                                       @RequestParam @Min(value = 1, message = "页码不得少于1")
                                                   Integer page,
                                       @RequestParam @Min(value = 1, message = "每页条数不得少于1")
                                                     @Max(value = 100, message = "每页条数不大于100")
                                                   Integer pageNum ){
        return transferLogic.page(new Page<>(page, pageNum), new QueryWrapper<>(transfer));
    }
}

