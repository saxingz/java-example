package org.saxing.emqtest;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;


public class AliApp {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    public static void main(String[] args) {

        testPublish("299457681406785544");

    }

    public static void testPublish(String clientId) {
        String pubTopic = "tt_kid_iot_attendance";
        int qos = 1;
        String broker = "tcp://post-cn-4591gxac810.mqtt.aliyuncs.com:1883";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("LTAI4GH9DWpQ4xkogaioiZJn");
            connOpts.setPassword("9w47bXj4A86pwxdSSyjO9FVAt3hAVR".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new OnMessageCallback());

            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);


            System.out.println("Connected");
            System.out.println("Publishing message");


            for (int i = 0; i < 1; i++) {

                String json = "{\"data\":[{\"userId\":\"335656625845702660\",\"authType\":\"2\"," +
                        "\"bizTime\":\"2020-11-25 20:48:26\",\"flowId\":\"" + getUUID() + "\"}]," +
                        "\"deviceId\":\"195858975537970185\",\"orgId\":\"155366507893940227\"," +
                        "\"requestId\":\""+ getUUID() +"\"}";

                // 消息发布所需参数
                MqttMessage message = new MqttMessage(json.getBytes());
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