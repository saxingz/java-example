package org.saxing.a0040_sensitive.entity;


/**
 * 审计方式
 *
 * @author liuhan 2020/4/11 17:09
 */
public class AuditWay extends BasicCode {
    private static final long serialVersionUID = 6876401121063035466L;

    // 1 = 自建词库
    public static final AuditWay SELF_WORD = new AuditWay("自建词库", 1);

    // 2 = 自研AI
    public static final AuditWay SELF_AI = new AuditWay("自研AI", 2);

    // 3 = 阿里AI
    public static final AuditWay ALI_AI = new AuditWay("阿里AI", 3);

    // 4 = 网易AI
    public static final AuditWay NETEASE_AI = new AuditWay("网易AI", 4);


    public AuditWay() {
    }

    public AuditWay(String name, Integer code) {
        super(name, code);
    }
}
