package com.test.splitwise.service;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  private final Integer TIMEOUT = 10000;

  public void setValue(String key, Object value) {
    redisTemplate.opsForValue().set(key, value, TIMEOUT, TimeUnit.SECONDS);
  }

  public Object getValue(String key) {
    return redisTemplate.opsForValue().get(key);
  }
}
