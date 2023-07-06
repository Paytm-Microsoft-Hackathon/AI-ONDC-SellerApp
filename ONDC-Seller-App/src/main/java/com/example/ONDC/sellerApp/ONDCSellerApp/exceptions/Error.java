package com.example.ONDC.sellerApp.ONDCSellerApp.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
  @JsonProperty("error")
  private ErrorMessage errorMessage;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ErrorMessage {
    @JsonProperty("message")
    private String message;
  }

  public String getMessage() {
    if(this.errorMessage == null) return null;
    else return this.errorMessage.getMessage();
  }
}
