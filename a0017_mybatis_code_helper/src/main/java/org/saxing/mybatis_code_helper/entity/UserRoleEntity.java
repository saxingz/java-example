package org.saxing.mybatis_code_helper.entity;

/**
 * 用户角色
 *
 * @author 刘罕  2018/8/2 11:41
 */
public class UserRoleEntity extends BasicEntity {
    private static final long serialVersionUID = 1060370024215967493L;

    private String userId;
    private String clientType;

    // 做一定的冗余设计，避免多表查询
    private Long roleId;
    private String roleName;
    private RoleEntity roleEntity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
