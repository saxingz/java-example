package org.saxing.mq.rocketmq.base.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * AsyncProducer
 *
 * @author saxing 2020/11/29 15:38
 */
public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.50.47:9876;192.168.50.47:9877");
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("base", "Tag2", ("Hello world " + i).getBytes());
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("sendResult: " + sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("Throwable: " + e);
                }
            });
        }
        Thread.sleep(2000);
        producer.shutdown();
    }

}
