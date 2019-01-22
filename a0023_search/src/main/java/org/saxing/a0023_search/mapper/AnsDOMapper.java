package org.saxing.a0023_search.mapper;

import org.saxing.a0023_search.domain.entity.AnsDO;

public interface AnsDOMapper {
    /**
     *  根据主键删除数据库的记录,ans
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,ans
     *
     * @param record
     */
    int insert(AnsDO record);

    /**
     *  动态字段,写入数据库记录,ans
     *
     * @param record
     */
    int insertSelective(AnsDO record);

    /**
     *  根据指定主键获取一条数据库记录,ans
     *
     * @param id
     */
    AnsDO selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,ans
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AnsDO record);

    /**
     *  根据主键来更新符合条件的数据库记录,ans
     *
     * @param record
     */
    int updateByPrimaryKey(AnsDO record);
}