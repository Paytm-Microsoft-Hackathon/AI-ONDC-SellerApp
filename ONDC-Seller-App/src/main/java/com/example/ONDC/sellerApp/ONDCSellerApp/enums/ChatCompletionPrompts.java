package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ChatCompletionPrompts {

  ENHANCE_TITLE_TONALITY_SETUP("Act like a senior seo analyst and rephrase the product title - %s for my e-commerce product of %s category and  make it appealing to customers by keeping it short concise"),
  ENHANCE_TITLE_SHORT_LEARNING_INPUT("Carrot 100g"),
  ENHANCE_TITLE_SHORT_LEARNING_OUTPUT("Fresh & Nutritious: 100g of Crispy Carrots"),
  ENHANCE_TITLE_SHORT_LEARNING_INPUT_V2("Lays 100g"),
  ENHANCE_TITLE_SHORT_LEARNING_OUTPUT_V2("Crunch into the Perfect Snack: Lays 100g"),
  ENHANCE_TITLE_SHORT_LEARNING_INPUT_V3("Wheat flour"),
  ENHANCE_TITLE_SHORT_LEARNING_OUTPUT_V3("Whear Flour: High-Quality Wheat Flour for Your Grocery Needs"),
  ENHANCE_TITLE_SHORT_LEARNING_INPUT_V4("Chicken Burger"),
  ENHANCE_TITLE_SHORT_LEARNING_OUTPUT_V4("Juicy Delights: Savory Chicken Burger for Food Lovers"),
  ENHANCE_DESCRIPTION_TONALITY_SETUP("You are a system used by seller to help them convert provided description of an ecommerce product into a fancy title which attracts customers"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_INPUT("Tasty momos"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_OUTPUT("Savory sensation: Delightful momos for every plate");
  private String prompt;

  public String getValue() {
    return prompt;
  }
}
