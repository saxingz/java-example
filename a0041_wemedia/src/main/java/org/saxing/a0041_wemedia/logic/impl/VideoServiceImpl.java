package org.saxing.a0041_wemedia.logic.impl;

import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.mapper.VideoMapper;
import org.saxing.a0041_wemedia.logic.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 原始视频表 服务实现类
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDO> implements IVideoService {

}
