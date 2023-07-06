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
  ENHANCE_DESCRIPTION_SHORT_LEARNING_OUTPUT("Savory sensation: Delightful momos for every plate"),
  GENERATE_DESCRIPTION_TONALITY_SETUP("You are a system used by seller to help them provide appealing description of the product and it's uses according to the title of an ecommerce product"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT("rawa"),
  GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT("Indulge in the finest quality of rawa (semolina) for your culinary creations. Our premium rawa is carefully sourced and milled to perfection, ensuring its superior texture and taste. Ideal for making delicious upma, halwa, and other mouthwatering dishes. Elevate your cooking experience with our top-notch rawa today!"),
  GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP("You are a system used by seller to help them provide nutritional benefits of the product according to the title of an ecommerce product"),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT("Rawa, also known as semolina, is a versatile and nutritious ingredient. It is an excellent source of complex carbohydrates, providing sustained energy. Rich in fiber, rawa aids digestion and promotes satiety. Packed with essential minerals like iron and magnesium, it supports overall health. Incorporate rawa into your diet for a wholesome and fulfilling experience."),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT("tomato"),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT_V2("Tomatoes are a nutritional powerhouse. They are low in calories but high in vitamins A and C, promoting healthy skin and a robust immune system. Tomatoes are rich in antioxidants like lycopene, which supports heart health. With a good amount of fiber, they aid digestion. Enjoy the tangy goodness of tomatoes for a nutritious boost.");
  private String prompt;

  public String getValue() {
    return prompt;
  }
}
