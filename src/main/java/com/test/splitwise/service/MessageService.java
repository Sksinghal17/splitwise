package com.test.splitwise.service;

import com.test.splitwise.model.entity.Message;
import com.test.splitwise.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  private MessageRepo messageRepo;

  public void saveMessage(Message message) {
    messageRepo.save(message);
  }

}
