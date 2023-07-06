package com.example.ONDC.sellerApp.AIProductCataloging.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
  public static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

  public static String formatDate(LocalDateTime dateTime) {
    return DATE_TIME_FORMATTER.format(dateTime);
  }
}
