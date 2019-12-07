package org.saxing.a0039_alimq;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQConsumer;
import com.aliyun.mq.http.MQProducer;
import com.aliyun.mq.http.common.AckMessageException;
import com.aliyun.mq.http.model.Message;
import com.aliyun.mq.http.model.TopicMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class A0039AlimqApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMqConsume(){
        MQClient mqClient = new MQClient(
                // 设置HTTP接入域名（此处以公共云生产环境为例）
                "http://.mqrest.cn-hangzhou.aliyuncs.com",
                // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
                "",
                // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
                ""
        );

        // 所属的 Topic
        final String topic = "accesslog";
        // 您在控制台创建的 Consumer ID(Group ID)
        final String groupId = "";
        // Topic所属实例ID，默认实例为空
        final String instanceId = "";

        final MQConsumer consumer;
        if (instanceId != null && instanceId != "") {
            consumer = mqClient.getConsumer(instanceId, topic, groupId, null);
        } else {
            consumer = mqClient.getConsumer(topic, groupId);
        }

        // 在当前线程循环消费消息，建议是多开个几个线程并发消费消息
        do {
            List<Message> messages = null;

            try {
                // 长轮询消费消息
                // 长轮询表示如果topic没有消息则请求会在服务端挂住3s，3s内如果有消息可以消费则立即返回
                messages = consumer.consumeMessage(
                        3,// 一次最多消费3条(最多可设置为16条)
                        3// 长轮询时间3秒（最多可设置为30秒）
                );
            } catch (Throwable e) {
                e.printStackTrace();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            // 没有消息
            if (messages == null || messages.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ": no new message, continue!");
                continue;
            }

            // 处理业务逻辑
            for (Message message : messages) {
                System.out.println("Receive message: " + message);
            }

            // Message.nextConsumeTime前若不确认消息消费成功，则消息会重复消费
            // 消息句柄有时间戳，同一条消息每次消费拿到的都不一样
            {
                List<String> handles = new ArrayList<String>();
                for (Message message : messages) {
                    handles.add(message.getReceiptHandle());
                }

                try {
                    consumer.ackMessage(handles);
                } catch (Throwable e) {
                    // 某些消息的句柄可能超时了会导致确认不成功
                    if (e instanceof AckMessageException) {
                        AckMessageException errors = (AckMessageException) e;
                        System.out.println("Ack message fail, requestId is:" + errors.getRequestId() + ", fail handles:");
                        if (errors.getErrorMessages() != null) {
                            for (String errorHandle :errors.getErrorMessages().keySet()) {
                                System.out.println("Handle:" + errorHandle + ", ErrorCode:" + errors.getErrorMessages().get(errorHandle).getErrorCode()
                                        + ", ErrorMsg:" + errors.getErrorMessages().get(errorHandle).getErrorMessage());
                            }
                        }
                        continue;
                    }
                    e.printStackTrace();
                }
            }
        } while (true);
    }

    @Test
    public void testMqProduce(){
        MQClient mqClient = new MQClient(
                // 设置HTTP接入域名（此处以公共云生产环境为例）
                "http://.mqrest.cn-hangzhou.aliyuncs.com",
                // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
                "",
                // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
                ""
        );

        // 所属的 Topic
        final String topic = "accesslog";
        // Topic所属实例ID，默认实例为空
        final String instanceId = "MQ_INST_1125983196457220_Bbm7jqJc";

        // 获取Topic的生产者
        MQProducer producer;
        if (instanceId != null && instanceId != "") {
            producer = mqClient.getProducer(instanceId, topic);
        } else {
            producer = mqClient.getProducer(topic);
        }

        try {
            // 循环发送4条消息
            for (int i = 0; i < 4; i++) {
                TopicMessage pubMsg;
                if (i % 2 == 0) {
                    // 普通消息
                    pubMsg = new TopicMessage(
                            // 消息内容
                            "hello mq!".getBytes(),
                            // 消息标签
                            "A"
                    );
                    // 设置属性
                    pubMsg.getProperties().put("a", String.valueOf(i));
                    // 设置KEY
                    pubMsg.setMessageKey("MessageKey");
                } else {
                    pubMsg = new TopicMessage(
                            // 消息内容
                            "hello mq!".getBytes(),
                            // 消息标签
                            "A"
                    );
                    // 设置属性
                    pubMsg.getProperties().put("a", String.valueOf(i));
                    // 定时消息, 定时时间为10s后
                    pubMsg.setStartDeliverTime(System.currentTimeMillis() + 10 * 1000);
                }
                // 同步发送消息，只要不抛异常就是成功
                TopicMessage pubResultMsg = producer.publishMessage(pubMsg);

                // 同步发送消息，只要不抛异常就是成功
                System.out.println(new Date() + " Send mq message success. Topic is:" + topic + ", msgId is: " + pubResultMsg.getMessageId()
                        + ", bodyMD5 is: " + pubResultMsg.getMessageBodyMD5());
            }
        } catch (Throwable e) {
            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
            System.out.println(new Date() + " Send mq message failed. Topic is:" + topic);
            e.printStackTrace();
        }

        mqClient.close();
    }

}
