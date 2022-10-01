package com.redis.redispubsub.sender;

import com.redis.redispubsub.retry.MessageDeliveryRetry;
import com.redis.redispubsub.retry.RetryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisherService implements MessagePublisher {

    private static final String CHANNEL_NAME = "chat";

    @Autowired
    private MessageDeliveryRetry messageDeliveryRetry;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RetryExecutor retryExecutor;
    @Autowired
    private ChannelTopic channelTopic;

    public MessagePublisherService(RedisTemplate<String, Object> redisTemplate, ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    public MessagePublisherService() {
    }

    public void handleMessage(String message) {
        try {
            redisTemplate.convertAndSend(channelTopic.getTopic(), message);
        } catch (Exception e) {
            messageDeliveryRetry.retry();
            retryExecutor.execute(() -> handleMessage(message), messageDeliveryRetry);
        }
    }
}
