package com.obcompany.subscribers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfiguration {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter){
           RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
           redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
           redisMessageListenerContainer.addMessageListener(messageListenerAdapter,topic());
           return redisMessageListenerContainer;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter()
    {
        return new MessageListenerAdapter(new RedisMessageSubscriber(),"onMessage");
    }
    
    @Value("${spring.redis.topic}")
    private String topicName;

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic(topicName);
    }

    @Bean
    RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }

}