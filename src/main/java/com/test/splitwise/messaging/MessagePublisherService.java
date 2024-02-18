package com.test.splitwise.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisherService {

  @Autowired
  private StringRedisTemplate redisTemplate;

  public void publishMessage(String message) {
    redisTemplate.convertAndSend("shubham_channel", message);
  }

}
