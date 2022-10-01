package com.redis.redispubsub.sender;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

public class SubsricbeRedis {
    private final RedisTemplate<String, Object> redisTemplate;
    private final String channelName;
    private final MessageListener messageListener;

    public SubsricbeRedis(RedisTemplate<String, Object> redisTemplate, String channelName, MessageListener messageListener) {
        this.redisTemplate = redisTemplate;
        this.channelName = channelName;
        this.messageListener = messageListener;
    }

    public void subscribe() {
        redisTemplate.getConnectionFactory().getConnection().subscribe(messageListener, channelName.getBytes());
    }
}
