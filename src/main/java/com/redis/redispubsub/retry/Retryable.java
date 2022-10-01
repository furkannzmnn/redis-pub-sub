package com.redis.redispubsub.retry;

@FunctionalInterface
public interface Retryable {
    void execute();
}
