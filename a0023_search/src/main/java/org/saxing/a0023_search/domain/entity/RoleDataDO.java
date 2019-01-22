package org.saxing.a0023_search.domain.entity;

public class RoleDataDO {
    /**
     *  主键,所属表字段为role_data.id
     */
    private Long id;

    /**
     *  ,所属表字段为role_data.role_id
     */
    private Long roleId;

    /**
     *  数据范围 0=无数据权限 1=仅自己 2=部门 3=部门及以下 4=指定数据 5=所有权限,所属表字段为role_data.range
     */
    private Integer range;

    /**
     *  具体数据,所属表字段为role_data.specific
     */
    private String specific;

    /**
     * 获取 主键 字段:role_data.id
     *
     * @return role_data.id, 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键 字段:role_data.id
     *
     * @param id role_data.id, 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取  字段:role_data.role_id
     *
     * @return role_data.role_id, 
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置  字段:role_data.role_id
     *
     * @param roleId role_data.role_id, 
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取 数据范围 0=无数据权限 1=仅自己 2=部门 3=部门及以下 4=指定数据 5=所有权限 字段:role_data.range
     *
     * @return role_data.range, 数据范围 0=无数据权限 1=仅自己 2=部门 3=部门及以下 4=指定数据 5=所有权限
     */
    public Integer getRange() {
        return range;
    }

    /**
     * 设置 数据范围 0=无数据权限 1=仅自己 2=部门 3=部门及以下 4=指定数据 5=所有权限 字段:role_data.range
     *
     * @param range role_data.range, 数据范围 0=无数据权限 1=仅自己 2=部门 3=部门及以下 4=指定数据 5=所有权限
     */
    public void setRange(Integer range) {
        this.range = range;
    }

    /**
     * 获取 具体数据 字段:role_data.specific
     *
     * @return role_data.specific, 具体数据
     */
    public String getSpecific() {
        return specific;
    }

    /**
     * 设置 具体数据 字段:role_data.specific
     *
     * @param specific role_data.specific, 具体数据
     */
    public void setSpecific(String specific) {
        this.specific = specific == null ? null : specific.trim();
    }
}