package com.redis.redispubsub.subsrictions;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class YoutubeListener implements MessageListener {
    public void receiveMessage(String message) {
        System.out.println("YOUTUBE : Received <" + message + ">");
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        receiveMessage(new String(message.getBody()));
    }
}
