package org.saxing.mq.rocketmq.base.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * SyncProducer
 *
 * @author saxing 2020/11/29 13:25
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("group2");
        producer.setNamesrvAddr("192.168.50.47:9876;192.168.50.47:9877");
        producer.start();
        for (int i = 0; i < 100000; i++) {
            Message msg = new Message("base", "Tag1", ("Hello world " + i).getBytes());
            SendResult result = producer.send(msg);
            SendStatus status = result.getSendStatus();
            String msgId = result.getMsgId();
            int queueId = result.getMessageQueue().getQueueId();
            System.out.print(" \n  【result  " + i + " 】 :" + result);
            System.out.print("  【msgId】: " + msgId);
            System.out.print("  【status】: " + status);
            System.out.print("  【queueId】: " + queueId);
        }
        System.out.println("发送完成");
        producer.shutdown();
    }

}
