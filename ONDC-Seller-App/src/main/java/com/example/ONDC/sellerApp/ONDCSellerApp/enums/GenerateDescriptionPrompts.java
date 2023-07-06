package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GenerateDescriptionPrompts {
  GENERATE_DESCRIPTION_TONALITY_SETUP("Act like a senior marketing person for a big ecommerce platform and write a description for a {category} item {title} and generate pointers for features, benefits in a beneficial tone"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT("sugar free"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT("A boon to diabetics and health conscious people \\n Ideal for cooking and baking as well as adding to beverages \\n Heat stable \\nHas negligible calories - Good for diabetics and weight-watchers\\n Features\\nHigh in protein\\n low in carbs\\nBenefits\\nLow fat helps with obesity\\nlow sugar helps with diabetes"),
  GENERATE_DESCRIPTION_TONALITY_SETUP_FOOD("Act like a chef for 5 star restaurant and write a description for a food item  {title} in a brief tone"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT_FOOD("A boon to diabetics and health conscious people \\n Ideal for cooking and baking as well as adding to beverages \\n Heat stable \\nHas negligible calories - Good for diabetics and weight-watchers\\n Features\\nHigh in protein\\n low in carbs\\nBenefits\\nLow fat helps with obesity\\nlow sugar helps with diabetes"),
  GENERATE_DESCRIPTION_TONALITY_SETUP_FASHION("Act like a fashion designer and write a short description for a fashion product {title} in a beneficial tone"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT_FASHION("Introducing the Louis Philippe Men Pure Cotton Slim Fit Solid Formal Shirt, a perfect blend of style and comfort for the modern gentleman. \nCrafted from premium quality cotton, this shirt ensures superior breathability and comfort, making it suitable for all-day wear.");
  private String prompt;

  public String getValue() {
    return prompt;
  }
}
