package com.example.ONDC.sellerApp.AIProductCataloging.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum AzureEndpoints {
  FILTER_ADULT_CONTENT("https://paytm-cognitive1.cognitiveservices.azure.com/vision/v3.2/analyze"),
  CHAT_COMPLETION("https://paytm-resource1.openai.azure.com/openai/deployments/gpt-35-turbo/chat/completions");

  private String url;

  public String getValue() {
    return url;
  }
}
