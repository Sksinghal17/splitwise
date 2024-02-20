package com.test.splitwise.service;

import com.google.gson.Gson;
import com.test.splitwise.messaging.MessagePublisherService;
import com.test.splitwise.model.entity.Message;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class TelegramHelperService {

  @Autowired
  private MessageService messageService;

  @Autowired
  private MessagePublisherService messagePublisherService;

  public String processTextMessage(String message) {
    if (message.equalsIgnoreCase("start")) {
      return "started";
    } else if (message.equalsIgnoreCase("hi")) {
      return "hi bro";
    } else if (message.equalsIgnoreCase("baki sab theek") || message.equalsIgnoreCase(
        "baki sab thik")) {
      return "bas chal rha hain";
    } else {
      return "unknown";
    }
  }

  public void saveTextMessage(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      messageService.saveMessage(
          Message.builder().username(update.getMessage().getFrom().getUserName())
              .textMessage(update.getMessage().getText()).build());
//      messagePublisherService.publishMessage(new Gson().toJson(update));
    }
  }

  public File createFileFromByteArray(byte[] byteArray, String fileName) throws IOException {
    File file = new File(fileName);
    try (FileOutputStream fos = new FileOutputStream(
        file); ByteArrayInputStream bis = new ByteArrayInputStream(byteArray)) {
      byte[] buffer = new byte[100000];
      int bytesRead;
      while ((bytesRead = bis.read(buffer)) != -1) {
        fos.write(buffer, 0, bytesRead);
      }
    }
    return file;
  }

}
