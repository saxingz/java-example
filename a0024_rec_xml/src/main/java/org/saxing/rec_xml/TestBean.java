package org.saxing.rec_xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class TestBean {

    public String ToUserName;

    public String FromUserName;

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
}
