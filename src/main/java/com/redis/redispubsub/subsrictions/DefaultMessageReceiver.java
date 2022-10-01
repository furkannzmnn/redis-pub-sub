package com.redis.redispubsub.subsrictions;

import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DefaultMessageReceiver implements MessageListener {

    private final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(DefaultMessageReceiver.class);

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        atomicInteger.incrementAndGet();
    }

    public int getAtomicInteger() {
        return atomicInteger.get();
    }

    @Override
    public void onMessage(org.springframework.data.redis.connection.Message message, byte[] pattern) {
        receiveMessage(new String(message.getBody()));
    }
}
