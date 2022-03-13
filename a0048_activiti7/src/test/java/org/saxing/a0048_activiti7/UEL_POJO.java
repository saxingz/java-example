package org.saxing.a0048_activiti7;

import java.io.Serializable;

/**
 * uel pojo test
 *
 * @author saxing 2022/3/14 0:07
 */
public class UEL_POJO implements Serializable {

    //属性必须全部小写
    private String zhixingren;
    private String pay;

    public String getZhixingren() {
        return zhixingren;
    }

    public void setZhixingren(String zhixingren) {
        this.zhixingren = zhixingren;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
