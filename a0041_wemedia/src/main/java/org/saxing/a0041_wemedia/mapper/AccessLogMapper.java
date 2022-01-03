package org.saxing.a0041_wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.saxing.a0041_wemedia.domain.entity.AccessLogDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 频道名称 Mapper 接口
 * </p>
 *
 * @author saxing
 * @since 2020-10-22
 */
@Repository
public interface AccessLogMapper extends BaseMapper<AccessLogDO> {

    List<AccessLogDO> queryByPath(@Param("tableName") String tableName, @Param("path") String path);

}
