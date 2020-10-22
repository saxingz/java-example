package org.saxing.a0041_wemedia.logic;

import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 原始视频表 服务类
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
public interface IVideoLogic extends IService<VideoDO> {


    /**
     * 下载视频
     * @param id id
     * @return bool
     */
    Boolean downloadVideo(Long id) throws IOException;
}
