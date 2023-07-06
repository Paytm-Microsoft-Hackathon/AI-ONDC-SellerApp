package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ChatCompletionPrompts {

  ENHANCE_TITLE_TONALITY_SETUP("You are a system used by seller to help them convert provided title of an ecommerce product into a fancy title which attracts customers"),
  ENHANCE_TITLE_SHORT_LEARNING_INPUT("momos"),
  ENHANCE_TITLE_SHORT_LEARNING_OUTPUT("Savory sensation: Delightful momos for every plate"),
  ENHANCE_DESCRIPTION_TONALITY_SETUP("You are a system used by seller to help them convert provided description of an ecommerce product into a fancy title which attracts customers"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_INPUT("Tasty momos"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_OUTPUT("Savory sensation: Delightful momos for every plate");
  private String prompt;

  public String getValue() {
    return prompt;
  }
}
