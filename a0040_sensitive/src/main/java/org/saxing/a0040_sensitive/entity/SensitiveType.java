package org.saxing.a0040_sensitive.entity;

/**
 * 敏感类型
 *
 * @author liuhan 2020/4/11 17:09
 */
public class SensitiveType extends BasicCode {
    private static final long serialVersionUID = 6876401121063035466L;

    // 0 = 正常
    public static final SensitiveType NORMAL = new SensitiveType("正常", 0);

    // 1=涉政暴恐
    public static final SensitiveType POLITICS = new SensitiveType("涉政暴恐", 1);

    // 2=色情
    public static final SensitiveType PORN = new SensitiveType("色情", 2);

    // 3=广告
    public static final SensitiveType AD = new SensitiveType("广告", 3);

    // 4=辱骂
    public static final SensitiveType ABUSE = new SensitiveType("辱骂", 4);

    // 5=其他
    public static final SensitiveType OTHER = new SensitiveType("其他", 5);


    public SensitiveType() {
    }

    public SensitiveType(String name, Integer code) {
        super(name, code);
    }
}
