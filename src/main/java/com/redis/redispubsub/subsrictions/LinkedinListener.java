package com.redis.redispubsub.subsrictions;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class LinkedinListener implements MessageListener {
    public void receiveMessage(String message) {
        System.out.println("LİNKEDİN : Received <" + message + ">");
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        receiveMessage(new String(message.getBody()));
    }
}
