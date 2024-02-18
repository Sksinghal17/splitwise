package com.test.splitwise.telegram;

import com.test.splitwise.service.VoiceService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBot extends TelegramLongPollingBot {


  @Override
  public String getBotUsername() {
    return "shubham_17_bot";
  }

  @Override
  public String getBotToken() {
    return "CHAT_API_TOKEN";
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage().getText();
      Long chatId = update.getMessage().getChatId();
      String response = processMessage(messageText);
      byte[] responseBytes;
      try {
        responseBytes = VoiceService.callVoiceRssApi(response);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      try {
        execute(new SendMessage(chatId.toString(), response));
        InputFile inputFile = new InputFile();
        File audioFile = createFileFromByteArray(responseBytes, response);
        inputFile.setMedia(audioFile);
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(chatId.toString());
        sendAudio.setAudio(inputFile);
        execute(sendAudio);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public String processMessage(String message) {
    if (message.equalsIgnoreCase("start")) {
      return "started";
    } else if (message.equalsIgnoreCase("hi")) {
      return "hi bro";
    } else {
      return "unknown";
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
