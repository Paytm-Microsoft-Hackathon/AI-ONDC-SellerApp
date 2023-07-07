package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponse {
  private String id;
  private String object;
  private long created;
  private String model;
  private List<ChatCompletionResponseChoices> choices;
  private ChatCompletionUsage usage;
}
