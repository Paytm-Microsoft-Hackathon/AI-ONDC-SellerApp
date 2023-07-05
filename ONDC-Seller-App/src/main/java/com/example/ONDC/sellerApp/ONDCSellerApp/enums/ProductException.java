package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ProductException {
  SOMETHING_WENT_WRONG("Something went wrong, Please try again later", "ONDC_500", HttpStatus.INTERNAL_SERVER_ERROR);
  private final String message;
  private final String code;
  private final HttpStatus status;

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
