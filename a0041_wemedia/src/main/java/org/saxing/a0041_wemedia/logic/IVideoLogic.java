package org.saxing.a0041_wemedia.logic;

import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 解析youtube json
     *
     * @param json json
     * @return res
     *
     */
    public List<VideoDO> parseYoutubeJson(String json);

}
