package org.saxing.mq.rocketmq.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * SyncProducer
 *
 * @author saxing 2020/11/29 13:25
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        TransactionMQProducer producer = new TransactionMQProducer("group1");
        producer.setNamesrvAddr("192.168.50.47:9876;192.168.50.47:9877");

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                if (StringUtils.equals("TAGA", msg.getTags())) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if (StringUtils.equals("TAGB", msg.getTags())) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else {
                    return LocalTransactionState.UNKNOW;
                }
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("消息的tag: " + msg.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        producer.start();

        String[] tags = {"TAGA","TAGB","TAGC"};

        for (int i = 0; i < 3; i++) {
            Message msg = new Message("TransactionTopic", tags[i], ("Hello world " + i).getBytes());
            SendResult result = producer.sendMessageInTransaction(msg, null);
            SendStatus status = result.getSendStatus();
            String msgId = result.getMsgId();
            int queueId = result.getMessageQueue().getQueueId();
            System.out.print(" \n  【result  " + i + " 】 :" + result);
            System.out.print("  【msgId】: " + msgId);
            System.out.print("  【status】: " + status);
            System.out.print("  【queueId】: " + queueId);
            System.out.println();
        }
        System.out.println("发送完成");
//        producer.shutdown();
    }

}
