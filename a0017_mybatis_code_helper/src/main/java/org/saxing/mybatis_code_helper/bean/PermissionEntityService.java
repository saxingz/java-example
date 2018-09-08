package org.saxing.mybatis_code_helper.bean;

import java.util.List;
import org.saxing.mybatis_code_helper.entity.PermissionEntity;
public interface PermissionEntityService{

    int insert(PermissionEntity permissionEntity);

    int insertSelective(PermissionEntity permissionEntity);

    int insertList(List<PermissionEntity> permissionEntitys);

    int update(PermissionEntity permissionEntity);
}
