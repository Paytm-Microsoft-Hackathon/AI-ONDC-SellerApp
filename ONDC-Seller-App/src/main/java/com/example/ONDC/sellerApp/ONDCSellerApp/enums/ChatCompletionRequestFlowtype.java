package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ChatCompletionRequestFlowtype {

  GENERATE_DESCRIPTION(1),
  GENERATE_ADDITIONAL_DESCRIPTION(2),
  ENHANCE_TITLE(3),
  ENHANCE_DESCRIPTION(4);

  private int value;

  public int getValue() {
    return value;
  }
}
