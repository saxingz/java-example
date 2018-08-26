package org.saxing.qisi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.qisi.config.WxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * MessageServiceTest
 *
 * Created by saxing on 2018/5/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private WxProperties wxProperties;

    @Test
    public void sendNewsMessage() throws Exception {
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1><span style=\"color: red;\">运营部 刘罕</span> 有了奇思妙想~</h1><br>\n" +
                "<h1>如何正确使用钞票。</h1><br>\n" +
                "<h1>速速点击阅读原文进行查看~</h1>\n" +
                "</body>\n" +
                "</html>";
        //@all
        messageService.sendNewsMessage("LiuHan", "digest", content, "msgTitle", "http://www.baidu.com", wxProperties.getMessageImg());
    }

}