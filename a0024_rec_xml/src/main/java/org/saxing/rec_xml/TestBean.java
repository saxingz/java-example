package org.saxing.rec_xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * test bean
 *
 * @author saxing 2019/2/26 15:15
 */
@XmlRootElement(name = "xml")
public class TestBean {

    public String ToUserName;

    public String FromUserName;

    public String content;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
