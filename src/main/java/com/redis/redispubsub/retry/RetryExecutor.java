package com.redis.redispubsub.retry;

import org.springframework.stereotype.Component;

@Component
public class RetryExecutor {

    private static final int MAX_RETRY_COUNT = 3;

    public boolean shouldRetry(MessageDeliveryRetry messageDeliveryRetry) {
        return messageDeliveryRetry.getRetryCount() <= MAX_RETRY_COUNT;
    }


    public void execute(Retryable retryable, MessageDeliveryRetry messageDeliveryRetry) {
        while (shouldRetry(messageDeliveryRetry)) {
                retryable.execute();
        }
    }

}
