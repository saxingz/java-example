package org.saxing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * properties
 *
 * Created by saxing on 17-10-31.
 */
@Component
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    private String CorpID;
    private String agentid;
    private String secret;
    private String domain;
    private String index;
    private String goodMediaId;
    private String badMediaId;

    public String getGoodMediaId() {
        return goodMediaId;
    }

    public void setGoodMediaId(String goodMediaId) {
        this.goodMediaId = goodMediaId;
    }

    public String getBadMediaId() {
        return badMediaId;
    }

    public void setBadMediaId(String badMediaId) {
        this.badMediaId = badMediaId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCorpID() {
        return CorpID;
    }

    public void setCorpID(String corpID) {
        CorpID = corpID;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
