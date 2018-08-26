package org.saxing.qisi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置
 *
 * Created by saxing on 2018/5/5.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    private String corpId;

    private String agentId;

    private String secret;

    private String domain;

    private String index;

    private String access;

    private String messageImg;

    private String tucaoImg;

    private String discussImg;

}
