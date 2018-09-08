package org.saxing.mybatis_code_helper.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * 角色
 * 
 * @author 刘罕  2018/8/2 11:23
 */
public class RoleEntity extends BasicEntity {
    private static final long serialVersionUID = -1187860755828731469L;
    
    private String boundStatus = BoundStatus.UNKNOWN.getValue();

    public String getBoundStatus() {
        return boundStatus;
    }

    public void setBoundStatus(String boundStatus) {
        if (!StringUtils.equals(boundStatus, BoundStatus.YES.getValue())
                && !StringUtils.equals(boundStatus, BoundStatus.NO.getValue())
                && !StringUtils.equals(boundStatus, BoundStatus.UNKNOWN.getValue())){
            throw new IllegalArgumentException("Mismatched bound status with value=" + boundStatus);
        }
        this.boundStatus = boundStatus;
    }
}
