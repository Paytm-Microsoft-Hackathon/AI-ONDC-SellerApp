package com.example.ONDC.sellerApp.ONDCSellerApp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class JsonUtil {
  public static <T, E> T parseJson(String data, Class<T> clazz) {
    try {
      ObjectMapper objectMapper =new ObjectMapper();
      return (Objects.nonNull(data) ? objectMapper.readValue(data, clazz) : null);
    } catch (Exception e) {
      log.error("Error in JsonUtil", e);
      return null;
    }
  }

  public static String serialiseJson(Object data) {

    try {
      ObjectMapper objectMapper =new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());
      return (Objects.nonNull(data) ? objectMapper.writeValueAsString(data) : null);
    } catch (JsonProcessingException e) {
      log.error("Error in JsonUtil", e);
      return null;
    }
  }
}
