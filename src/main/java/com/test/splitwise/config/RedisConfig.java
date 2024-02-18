package com.test.splitwise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Autowired
  private RedisProperties redisProperties;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName(redisProperties.getHost());
    jedisConnectionFactory.setPort(redisProperties.getPort());
    jedisConnectionFactory.setDatabase(redisProperties.getDatabase());
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    return redisTemplate;
  }

  @Bean
  public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
      MessageListener messageListener) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(messageListener, new ChannelTopic("shubham_channel"));
    return container;
  }
}
