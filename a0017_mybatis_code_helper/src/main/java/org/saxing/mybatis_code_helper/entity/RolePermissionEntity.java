package org.saxing.mybatis_code_helper.entity;

/**
 * 角色权限
 *
 * @author 刘罕  2018/8/2 11:25
 */
public class RolePermissionEntity extends BasicEntity {
    private static final long serialVersionUID = 8639940436260146055L;

    // 做一定的冗余设计，避免多表查询
    private Long roleId;
    private String roleName;
    private RoleEntity roleEntity;

    // 做一定的冗余设计，避免多表查询
    private Long permissionId;
    private String permissionName;
    private String permissionType;
    private PermissionEntity permissionEntity;

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

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public PermissionEntity getPermissionEntity() {
        return permissionEntity;
    }

    public void setPermissionEntity(PermissionEntity permissionEntity) {
        this.permissionEntity = permissionEntity;
    }
}
