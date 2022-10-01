package com.redis.redispubsub.config;

import com.redis.redispubsub.subsrictions.DefaultMessageReceiver;
import com.redis.redispubsub.subsrictions.LinkedinListener;
import com.redis.redispubsub.subsrictions.WhatsAppListener;
import com.redis.redispubsub.subsrictions.YoutubeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;


@Configuration
public class RedisConfig {


    // MessageListener
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    MessageListenerAdapter linkedin() {
        return new MessageListenerAdapter(new WhatsAppListener());
    }

    @Bean
    MessageListenerAdapter whatsApp() {
        return new MessageListenerAdapter(new LinkedinListener());
    }

    @Bean
    MessageListenerAdapter youtube() {
        return new MessageListenerAdapter(new YoutubeListener());
    }

    @Bean
    MessageListenerAdapter defaultListener() {
        return new MessageListenerAdapter(new DefaultMessageReceiver());
    }


    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(whatsApp(), topic());
        container.addMessageListener(linkedin(), topic());
        container.addMessageListener(youtube(), topic());
        container.addMessageListener(defaultListener(), topic());
        return container;
    }


    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("chat");
    }


}
