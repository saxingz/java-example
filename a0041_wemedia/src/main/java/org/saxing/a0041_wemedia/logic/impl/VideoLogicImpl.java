package org.saxing.a0041_wemedia.logic.impl;

import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.mapper.VideoMapper;
import org.saxing.a0041_wemedia.logic.IVideoLogic;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 原始视频表 服务实现类
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@Service
public class VideoLogicImpl extends ServiceImpl<VideoMapper, VideoDO> implements IVideoLogic {

    @Override
    public List<VideoDO> parseYoutubeJson(String json) {

        return null;
    }
}
