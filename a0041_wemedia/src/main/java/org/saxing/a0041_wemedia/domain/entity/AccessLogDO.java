/*
 * Copyright (c) 2015-2019 Au Smart Co.,Ltd. to Present.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Au Smart Co.,Ltd.
 * You shall not disclose such confidential information
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with www.au-smart.cn.
 */
package org.saxing.a0041_wemedia.domain.entity;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * access_log
 *
 * @author liuhan 2019/12/4 14:15
 */
@Data
@ToString
public class AccessLogDO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 访问id
     */
    private String accessId;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 路径根，用于区分是哪一个服务系统
     */
    private String pathRoot;

    /**
     * 自定义业务划分 7kid,operate,payment
     */
    private String bizSys;

    /**
     * 自定义业务模块，预留
     */
    private String bizType;

    /**
     * 访问方法
     */
    private String method;

    /**
     * http状态码
     */
    private String statusCode;

    /**
     * 访问人
     */
    private String accountId;

    /**
     * 设备信息
     */
    private String userAgent;

    /**
     * 授权信息
     */
    private String authorization;

    /**
     * 查询参数
     */
    private String queryParams;

    /**
     * 表单内容
     */
    private String formParams;

    /**
     * 请求体
     */
    private String requestBody;

    /**
     * 返回体
     */
    private String responseBody;

    /**
     * app版本 7kid_teacher_1.0.11
     */
    private String appVersion;

    /**
     * 客户端版本 android ios web
     */
    private String clientType;

    /**
     * 客户端品牌
     */
    private String clientBrand;

    /**
     * 客户端系统
     */
    private String clientVersion;

    /**
     * 客户端 id
     */
    private String clientId;

    /**
     * 客户端语言
     */
    private String lang;

    /**
     * 远端ip
     */
    private String remoteIp;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 调用时长，毫秒
     */
    private Long proceedTime;

    /**
     * 请求消息 key
     */
    private String requestMsgMqKey;

    /**
     * 响应消息 key
     */
    private String responseMsgMqKey;

    /**
     * 所有header
     */
    private String headers = "";

    /**
     * 是否删除(0=正常,1=删除)
     */
    private Boolean isDeleted;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}