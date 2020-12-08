package org.saxing.a0044mqttv5;


import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;

import java.util.UUID;

public class ConsumerDemo {

    public static void main(String[] args) {
        String topic        = "testExamples/p2p/1";
        String broker       = "tcp://192.168.50.47:1883";
        String clientId     = "JavaConsumerSample-2";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttConnectionOptions connOpts = new MqttConnectionOptions();
            connOpts.setCleanStart(false);
            // session 一天过期
            connOpts.setSessionExpiryInterval(86400L);
            connOpts.setUserName("admin");
            connOpts.setPassword("password".getBytes());
            MqttAsyncClient sampleClient = new MqttAsyncClient(broker, clientId, persistence);
            System.out.println("Connecting to broker: " + broker);
            IMqttToken token = sampleClient.connect(connOpts);
            token.waitForCompletion();
            System.out.println("Connected");
            int[] topicQos = {0,1,2};
            String[] topicNames = new String[]{topic};
            IMqttToken subscribe = sampleClient.subscribe(topicNames, topicQos, null, null);
            sampleClient.setCallback(new MessageReceiver(clientId));
            subscribe.waitForCompletion();

//            System.out.println("Disconnected");
//            System.out.println("Close client.");
//            sampleClient.disconnect();
//            sampleClient.close();
//            System.exit(0);
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
