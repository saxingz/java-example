package org.saxing.a0040_sensitive.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 审计详情
 *
 * @author liuhan 2020/4/11 17:43
 */
@Data
@Builder
public class AuditTextDetailDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 请求id
     */
    private Long reqId;

    /**
     * 审计方式 1=自建词库 2=自研ai 3=阿里云ai 4=网易ai
     */
    private Integer way;

    /**
     * 敏感类型 0=正常  1=涉政暴恐，2=色情，3=广告，4=辱骂，5=其他
     */
    private Integer type;

    /**
     * 违禁等级   0=正常，5=疑似 10=违规
     */
    private Integer level;

    /**
     * 特征点
     */
    private String feature;

    private static final long serialVersionUID = 1L;
}