package org.saxing.a0040_sensitive.entity;


/**
 * 审计违禁等级
 *
 * @author liuhan 2020/4/11 17:09
 */
public class AuditLevel extends BasicCode {
    private static final long serialVersionUID = 6876401121063035466L;

    // 0 = 正常
    public static final AuditLevel NORMAL = new AuditLevel("正常", 0);

    // 5 = 疑似
    public static final AuditLevel SELF_AI = new AuditLevel("疑似", 5);

    // 10 = 违规
    public static final AuditLevel ALI_AI = new AuditLevel("违规", 10);


    public AuditLevel() {
    }

    public AuditLevel(String name, Integer code) {
        super(name, code);
    }
}
