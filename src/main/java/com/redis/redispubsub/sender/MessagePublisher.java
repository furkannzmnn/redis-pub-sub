package com.redis.redispubsub.sender;

public interface MessagePublisher {
    void handleMessage(String message);
}
