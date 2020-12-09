package org.saxing.a0044mqttv5;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

public class MessageReceiver implements MqttCallback {

    private String clientId;

    public MessageReceiver(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void disconnected(MqttDisconnectResponse disconnectResponse) {
        System.out.println("disconnectResponse: " + disconnectResponse);
    }

    @Override
    public void mqttErrorOccurred(MqttException exception) {
        System.out.println("exception: " + exception);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("topic: " + topic);
        System.out.println("message: " + message);
    }

    @Override
    public void deliveryComplete(IMqttToken token) {
        System.out.println("token: " + token);
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        System.out.println("reconnect: " + serverURI);
    }

    @Override
    public void authPacketArrived(int reasonCode, MqttProperties properties) {
        System.out.println("reasonCode: " + reasonCode);
        System.out.println("properties: " + properties);
    }
}
