package com.test.splitwise;

import com.test.splitwise.telegram.TelegramBot;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class SplitwiseApplication {

  public static void main(String[] args) {
    SpringApplication.run(SplitwiseApplication.class, args);
  }

  @PostConstruct
  public void registerBot() {
    try {
      TelegramBot telegramBot = new TelegramBot();
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(telegramBot);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

}
