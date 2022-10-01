package com.redis.redispubsub.subsrictions;

public interface Receiver {
    void receiveMessage(String message);
}
