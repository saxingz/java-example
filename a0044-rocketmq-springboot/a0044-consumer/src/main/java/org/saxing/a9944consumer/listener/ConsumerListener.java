package org.saxing.a9944consumer.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ConsumerListener
 *
 * @author saxing 2020/11/30 0:01
 */
@RocketMQMessageListener(topic = "base", consumerGroup = "${rocketmq.consumer.group}")
@Component
public class ConsumerListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("收到消息： " + s);
    }
}
