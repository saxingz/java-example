package org.saxing.emqtest;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;


public class PublishApp {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> testPublish("emqx_test" + finalI)).start();
        }

    }

    public static void testPublish(String clientId) {
        String pubTopic = "testtopic/1";
        String content = "Hello World";
        int qos = 2;
        String broker = "tcp://192.168.50.47:1883";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("emqx_test");
            connOpts.setPassword("emqx_test_password".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new OnMessageCallback());

            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);


            System.out.println("Connected");
            System.out.println("Publishing message: " + content);


            for (int i = 0; i < 100; i++) {
                System.out.println("=======================      第 " + i + " 个");
                String toSendString = content + UUID.randomUUID().toString();
                // 消息发布所需参数
                MqttMessage message = new MqttMessage(toSendString.getBytes());
                message.setQos(qos);
                client.publish(pubTopic, message);
                System.out.println("Message published");
            }


            client.disconnect();
            System.out.println("Disconnected");
            client.close();
//            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}