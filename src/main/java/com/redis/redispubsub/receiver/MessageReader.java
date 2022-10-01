package com.redis.redispubsub.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class MessageReader {
    private final RedisTemplate<String, Object> template;

    public String read() {
        List<Object> messages = template.opsForList().range("chat", 0, -1);
        if (CollectionUtils.isEmpty(messages)) {
            return null;
        }
        IntStream.range(0, messages.size()).forEach(i -> template.opsForList().leftPop("chat"));
        return messages.get(0).toString();
    }

    public String readAllMessages() {
        StringBuilder builder = new StringBuilder();
        String message;
        while ((message = read()) != null) {
            builder.append(message);
        }
        return builder.toString();
    }
}

