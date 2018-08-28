package org.saxing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author saxing
 * @description: []
 * Created on 2017/10/31 23:23
 * modified By  []
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void sendTextMessageToUserTest(){
        messageService.sendTextMessageToUser("LiuHan", "你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看" +
                "<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。");
    }

    @Test
    public void sendTextCardMessageTest(){
        messageService.sendTextCardMessage("LiuHan", 1, "你被点赞了。");
    }

    @Test
    public void sendNewsMessageTest(){
        messageService.sendNewsMessage("1852659", 1, "这是简介", "<h1>恭喜你啦。</h1>", "xxx对xxx好评");
//        messageService.sendNewsMessage("LiuHan", -1, "这是简介", "<h1>被差评了吧。</h1>");
    }

}
