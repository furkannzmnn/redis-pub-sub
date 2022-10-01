package com.redis.redispubsub.retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MessageDeliveryRetry {

    private static final Logger LOGGER = LogManager.getLogger(MessageDeliveryRetry.class);

        private int retryCount = 0;

        public void retry() {
            LOGGER.info("Retrying message delivery");
            retryCount++;
        }

        public int getRetryCount() {
            return retryCount;
        }
}
