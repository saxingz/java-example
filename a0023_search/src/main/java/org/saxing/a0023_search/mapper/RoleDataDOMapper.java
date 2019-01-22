package org.saxing.a0023_search.mapper;


import org.saxing.a0023_search.domain.entity.RoleDataDO;

public interface RoleDataDOMapper {
    /**
     *  根据主键删除数据库的记录,role_data
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,role_data
     *
     * @param record
     */
    int insert(RoleDataDO record);

    /**
     *  动态字段,写入数据库记录,role_data
     *
     * @param record
     */
    int insertSelective(RoleDataDO record);

    /**
     *  根据指定主键获取一条数据库记录,role_data
     *
     * @param id
     */
    RoleDataDO selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,role_data
     *
     * @param record
     */
    int updateByPrimaryKeySelective(RoleDataDO record);

    /**
     *  根据主键来更新符合条件的数据库记录,role_data
     *
     * @param record
     */
    int updateByPrimaryKey(RoleDataDO record);
}