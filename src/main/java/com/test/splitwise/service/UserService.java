package com.test.splitwise.service;

import com.google.gson.Gson;
import com.test.splitwise.exception.SplitwiseException;
import com.test.splitwise.messaging.MessagePublisherService;
import com.test.splitwise.model.dto.UserDTO;
import com.test.splitwise.model.entity.User;
import com.test.splitwise.repos.UserRepo;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private RedisService redisService;

  @Autowired
  private MessagePublisherService messagePublisherService;

  private final Gson gson = new Gson();

  public User onboardUser(UserDTO userDTO) {
    User newUser = new User();
    newUser.setName(userDTO.getName());
    newUser.setEmail(userDTO.getEmail());
    newUser.setPassword(userDTO.getPassword());
    newUser.setPhoneNumber(userDTO.getPhoneNumber());
    newUser.setUserType(userDTO.getUserType());
    try {
      return userRepo.save(newUser);
    } catch (Exception e) {
      throw new SplitwiseException("Error creating user: " + userDTO.getName());
    }
  }

  public void changePassword(Integer userId, String newPassword) {
    log.info("Changing password for  user : {}", userId);
    Optional<User> userOptional = userRepo.findById(userId);
    userOptional.ifPresent(user -> {
      user.setPassword(newPassword);
      userRepo.save(user);
    });
  }

  public User getUser(Integer userId) {
    String redisKey = getKey(userId);
    messagePublisherService.publishMessage("fetching user for id : " + userId.toString());
    Object redisResult = redisService.getValue(redisKey);
    if (Objects.nonNull(redisResult)) {
      log.info("fetched user from redis");
      return gson.fromJson((String) redisResult, User.class);
    }
    Optional<User> userOptional = userRepo.findById(userId);
    userOptional.ifPresent(user -> redisService.setValue(redisKey, gson.toJson(user)));
    return userOptional.orElse(null);
  }

  private String getKey(Integer userId) {
    return "user-" + userId.toString();
  }
}

