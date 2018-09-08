package org.saxing.mybatis_code_helper.entity;


import org.apache.commons.lang3.StringUtils;

/**
 * 权限类型
 *
 * @author 刘罕  2018/8/2 11:02
 */
public class PermissionType extends BasicType {
    private static final long serialVersionUID = -7838429182851200458L;

    public static final PermissionType API = new PermissionType("接口权限", "API");
    public static final PermissionType GATEWAY = new PermissionType("网关权限", "GATEWAY");
    public static final PermissionType UI = new PermissionType("界面权限", "UI");

    public PermissionType() {
    }

    public PermissionType(String name, String value) {
        super(name, value);
    }

    public static PermissionType fromString(String value){
        if (StringUtils.equals(value, API.getValue())){
            return API;
        }else if(StringUtils.equals(value, GATEWAY.getValue())){
            return GATEWAY;
        }else if(StringUtils.equals(value, UI.getValue())){
            return UI;
        }
        return null;
    }
}
