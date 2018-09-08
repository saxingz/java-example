package org.saxing.mybatis_code_helper.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * 绑定状态
 *
 * @author 刘罕  2018/8/2 10:54
 */
public class BoundStatus extends BasicType {
    private static final long serialVersionUID = -4219298400593453894L;

    public static final BoundStatus YES = new BoundStatus("已绑定", "YES");
    public static final BoundStatus NO = new BoundStatus("未绑定", "NO");
    public static final BoundStatus UNKNOWN = new BoundStatus("未知", "UNKNOWN");

    public BoundStatus() {
        super();
    }

    public BoundStatus(String name, String value) {
        super(name, value);
    }

    public static BoundStatus fromString(String value){
        if (StringUtils.equals(value, YES.getValue())){
            return YES;
        }else if (StringUtils.equals(value, NO.getValue())){
            return NO;
        }else if (StringUtils.equals(value, UNKNOWN.getValue())){
            return UNKNOWN;
        }
        return null;
    }

}
