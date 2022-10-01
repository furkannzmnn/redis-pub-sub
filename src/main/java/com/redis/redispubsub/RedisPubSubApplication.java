package com.redis.redispubsub;

import com.redis.redispubsub.receiver.MessageReader;
import com.redis.redispubsub.sender.MessagePublisherService;
import com.redis.redispubsub.sender.SubsricbeRedis;
import com.redis.redispubsub.subsrictions.WhatsAppListener;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class RedisPubSubApplication {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RedisPubSubApplication.class, args);

        final MessagePublisherService bean = ctx.getBean(MessagePublisherService.class);

        bean.handleMessage("Message " + "EVENT PUBLISHED");

        final MessageReader bean1 = ctx.getBean(MessageReader.class);
        final String s = bean1.readAllMessages();
        System.out.println(s);
    }

}
