package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponseChoices {
  
  private int index;

  @JsonProperty("finish_reason")
  private String finishReason;

  private ChatCompletionResponseData message;
}
