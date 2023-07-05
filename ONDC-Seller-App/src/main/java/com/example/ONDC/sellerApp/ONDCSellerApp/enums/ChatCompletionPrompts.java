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
  ENHANCE_DESCRIPTION_SHORT_LEARNING_OUTPUT("Savory sensation: Delightful momos for every plate"),
  GENERATE_DESCRIPTION_TONALITY_SETUP("You are a system used by seller to help them provide appealing description of the product and it's uses according to the title of an ecommerce product"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT("rawa"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT("Indulge in the finest quality of rawa (semolina) for your culinary creations. Our premium rawa is carefully sourced and milled to perfection, ensuring its superior texture and taste. Ideal for making delicious upma, halwa, and other mouthwatering dishes. Elevate your cooking experience with our top-notch rawa today!");

  private String prompt;

  public String getValue() {
    return prompt;
  }
}
