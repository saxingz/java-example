package org.saxing.a0044mqttv5;


import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;

import java.util.UUID;

public class ProducerDemo {

    public static void main(String[] args) throws InterruptedException {


        int qos             = 1;
        String broker       = "tcp://192.168.50.47:1883";
        String clientId     = "JavaSample" + UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttConnectionOptions connOpts = new MqttConnectionOptions();
            connOpts.setCleanStart(false);
            connOpts.setUserName("admin");
            connOpts.setPassword("password".getBytes());
            MqttAsyncClient sampleClient = new MqttAsyncClient(broker, clientId, persistence);
            System.out.println("Connecting to broker: " + broker);
            IMqttToken token = sampleClient.connect(connOpts);
            token.waitForCompletion();
            System.out.println("Connected");
            for (long i = 0L; i < Long.MAX_VALUE; i++) {
                Thread.sleep(100);
                String topic = "testExamples/p2p/" + i % 10;
                String content = "Message from MqttPublishSample: " + i;
                System.out.println("Publishing message: "+content);
                long start = System.currentTimeMillis();
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                token = sampleClient.publish(topic, message);
                token.waitForCompletion();
                long end = System.currentTimeMillis();
                System.out.println("耗时： " + (end - start) + "ms");
            }

            System.out.println("Disconnected");
            System.out.println("Close client.");
            sampleClient.disconnect();
            sampleClient.close();
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

}
