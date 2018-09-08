package org.saxing.mybatis_code_helper.bean;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import org.saxing.mybatis_code_helper.entity.PermissionEntity;
import org.saxing.mybatis_code_helper.bean.PermissionEntityDao;
import org.saxing.mybatis_code_helper.bean.PermissionEntityService;

@Service
public class PermissionEntityServiceImpl implements PermissionEntityService{

    @Resource
    private PermissionEntityDao permissionEntityDao;

    @Override
    public int insert(PermissionEntity permissionEntity){
        return permissionEntityDao.insert(permissionEntity);
    }

    @Override
    public int insertSelective(PermissionEntity permissionEntity){
        return permissionEntityDao.insertSelective(permissionEntity);
    }

    @Override
    public int insertList(List<PermissionEntity> permissionEntitys){
        return permissionEntityDao.insertList(permissionEntitys);
    }

    @Override
    public int update(PermissionEntity permissionEntity){
        return permissionEntityDao.update(permissionEntity);
    }
}
