package org.saxing.mybatis_code_helper.bean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.saxing.mybatis_code_helper.entity.PermissionEntity;

@Mapper
public interface PermissionEntityDao {
    int insert(@Param("permissionEntity") PermissionEntity permissionEntity);

    int insertSelective(@Param("permissionEntity") PermissionEntity permissionEntity);

    int insertList(@Param("permissionEntitys") List<PermissionEntity> permissionEntitys);

    int update(@Param("permissionEntity") PermissionEntity permissionEntity);
}
