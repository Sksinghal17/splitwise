package com.test.splitwise.telegram;

import com.test.splitwise.service.TelegramHelperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

  private final TelegramHelperService telegramHelperService;


  @Override
  public String getBotUsername() {
    return "shubham_17_bot";
  }

  @Override
  public String getBotToken() {
    return "API_TOKEN";
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage().getText();
      Long chatId = update.getMessage().getChatId();
      String response = telegramHelperService.processTextMessage(messageText);
      try {
        execute(new SendMessage(chatId.toString(), response));
        telegramHelperService.saveTextMessage(update);
      } catch (TelegramApiException e) {
        log.error(e.getMessage());
      }
    }
  }

}
