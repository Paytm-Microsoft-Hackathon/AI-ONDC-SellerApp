package com.example.ONDC.sellerApp.ONDCSellerApp.enums;


import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.*;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.FASHION;


public enum EnhanceDescriptionSetToneInPrompt {
  GROCERY_PROMPT(GROCERY, "An e-commerce product image of a plate of %s  in the grocery category under studio lighting."),
  PACKED_ITEMS_PROMPT(PACKED_ITEMS, ""),
  FRUITS_AND_VEGETABLES_PROMPT(FRUITS_AND_VEGETABLES, "A closeup e-commerce product image of a set of more than 1 %s in the Fruits and Vegetables category under studio lighting."),
  FOOD_PROMPT(FOOD, "A realistic e-commerce product image of a full plate of %s in the foods category under studio lighting."),
  BEVERAGES_PROMPT(BEVERAGES, "A realistic e-commerce product image of a glass of %s in the beverages category under studio lighting."),
  FASHION_PROMPT(FASHION, "act like a senior seo analyst and rephrase the product description - White tshirt, cotton fabric, washable, breathable fabric, and contains elastane for my e-commerce product of fashion category which is a Mens white Tshirt to make it appealing to customers and generate pointers for features, benefits in a beneficial tone");

  private ProductCategory productCategory;
  private String promptMessage;

  EnhanceDescriptionSetToneInPrompt(ProductCategory productCategory, String promptMessage) {
    this.productCategory = productCategory;
    this.promptMessage = promptMessage;
  }

  public String getpromptMessage() {
    return promptMessage;
  }


  public static EnhanceDescriptionSetToneInPrompt fromCategory(ProductCategory productCategory) {
    return switch (productCategory) {
      case GROCERY -> GROCERY_PROMPT;
      case PACKED_ITEMS -> PACKED_ITEMS_PROMPT;
      case FRUITS_AND_VEGETABLES -> FRUITS_AND_VEGETABLES_PROMPT;
      case FOOD -> FOOD_PROMPT;
      case BEVERAGES -> BEVERAGES_PROMPT;
      case FASHION -> FASHION_PROMPT;
    };
  }

}
