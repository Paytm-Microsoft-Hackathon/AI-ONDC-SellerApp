package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionUsage {

  @JsonProperty("completion_tokens")
  private int completionTokens;

  @JsonProperty("prompt_tokens")
  private int promptTokens;

  @JsonProperty("total_tokens")
  private int totalTokens;
}
