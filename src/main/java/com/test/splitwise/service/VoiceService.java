package com.test.splitwise.service;


import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class VoiceService {

  private static final String API_KEY = "API_KEY";
  private static final String LANGUAGE = "en-us";

  public static byte[] callVoiceRssApi(String textToConvert) throws IOException {
    String apiUrl = String.format("http://api.voicerss.org/?key=%s&hl=%s&src=%s", API_KEY, LANGUAGE,
        textToConvert.replaceAll(" ", "%20"));

    HttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(apiUrl);

    HttpResponse response = httpClient.execute(httpGet);
    HttpEntity entity = response.getEntity();

    if (entity != null) {
      try (InputStream inputStream = entity.getContent()) {
        return inputStream.readAllBytes();
      }
    } else {
      throw new IOException("No response from API");
    }
  }

}
