package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.BEVERAGES;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.FASHION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.FOOD;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.FRUITS_AND_VEGETABLES;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.GROCERY;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory.PACKED_ITEMS;

public enum ImageGenerationPrompt {
  GROCERY_PROMPT(GROCERY, "An e-commerce product image of a plate of %s  in the grocery category under studio lighting."),
  PACKED_ITEMS_PROMPT(PACKED_ITEMS, ""),
  FRUITS_AND_VEGETABLES_PROMPT(FRUITS_AND_VEGETABLES, "A closeup e-commerce product image of a set of more than 1 %s in the Fruits and Vegetables category under studio lighting."),
  FOOD_PROMPT(FOOD, "A realistic e-commerce product image of a full plate of %s in the foods category under studio lighting."),
  BEVERAGES_PROMPT(BEVERAGES, "A realistic e-commerce product image of a glass of %s in the beverages category under studio lighting."),
  FASHION_PROMPT(FASHION, "");

  private ProductCategory productCategory;
  private String promptMessage;

  ImageGenerationPrompt(ProductCategory productCategory, String promptMessage) {
    this.productCategory = productCategory;
    this.promptMessage = promptMessage;
  }

  public ProductCategory getProductCategory() {
    return productCategory;
  }

  public String getPromptMessage() {
    return promptMessage;
  }

  public static ImageGenerationPrompt fromCategory(ProductCategory productCategory) {
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
