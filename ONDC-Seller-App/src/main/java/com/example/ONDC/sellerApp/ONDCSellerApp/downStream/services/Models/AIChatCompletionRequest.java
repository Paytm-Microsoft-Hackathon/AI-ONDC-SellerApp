package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIChatCompletionRequest {

  private List<AIShortLearningDTO> messages;
  private int temperature = 1;

  @JsonProperty("top_p")
  private double topP = 0.95;

  @JsonProperty("frequency_penalty")
  private double frequencyPenalty = 0;

  @JsonProperty("presence_penalty")
  private double presencePenalty = 0;

  @JsonProperty("max_tokens")
  private int maxTokens;
}
