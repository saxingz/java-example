package org.saxing.a0041_wemedia.logic;

import cn.hutool.core.date.DateTime;
import org.saxing.a0041_wemedia.domain.entity.ChannelDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * <p>
 * 频道名称 服务类
 * </p>
 *
 * @author saxing
 * @since 2020-10-22
 */
public interface IChannelLogic extends IService<ChannelDO> {

    /**
     * 扫描频道
     */
    public void scanChannel(DateTime startDate, DateTime endDate, String channelId) ;

}
