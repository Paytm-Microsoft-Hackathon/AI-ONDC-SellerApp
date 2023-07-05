package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum AzureEndpoints {
  FILTER_ADULT_CONTENT("https://paytm-cognitive1.cognitiveservices.azure.com/vision/v3.2/analyze?visualFeatures=Adult");

  private String url;

  public String getValue() {
    return url;
  }
}
