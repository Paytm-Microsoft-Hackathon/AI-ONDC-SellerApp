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
  ENHANCE_DESCRIPTION_TONALITY_SETUP_FOOD("Act like a chef for 5 star restaurant and rephrase the description  {description} for a {food} item {title}"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_INPUT("a biryani made of pure basmati rice, healthy olive oil, fresh paneer"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_OUTPUT("Our delectable Paneer Biryani is crafted using only the finest ingredients. Fragrant and fluffy basmati rice is expertly cooked to perfection and infused with healthy olive oil. The dish is then layered with chunks of fresh and creamy paneer that add a delightful texture to the biryani. Every bite is a sensory delight as the spices used in this dish are carefully balanced to create an explosion of flavors that lingers on your palate."),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_INPUT_V2("A chicken burger with mayo, lettuce, high-quality breads."),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_OUTPUT_V2("Indulge in our mouth-watering Chicken Burger, made to perfection with only the finest ingredients. Each burger patty is crafted with succulent chicken that is grilled to perfection, ensuring it is tender and juicy. Sandwiched between a soft and freshly baked sesame seed bun, the patty is then topped with crisp lettuce, ripe tomato, and a delectable tangy sauce that perfectly complements the chicken. Our burger is then seasoned with aromatic herbs and spices, providing a burst of flavors in every bite."),
  ENHANCE_DESCRIPTION_TONALITY_SETUP_COMMON("act like a senior seo analyst and rephrase the product description , %s for my e-commerce product of %s which is a %s to make it appealing to customers and generate pointers for features, benefits in a positive tone"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_COMMON_INPUT("White tshirt, cotton fabric, washable, breathable fabric, and contains elastane"),
  ENHANCE_DESCRIPTION_SHORT_LEARNING_COMMON_OUTPUT("Introducing our Men's White Tshirt - a wardrobe essential for every fashion-savvy individual! Made from high-quality cotton fabric, our tshirt is not only stylish, but also comfortable, breathable, and easy to care for. The addition of elastane offers a subtle stretch for a comfortable, non-restrictive fit that maintains its shape wash after wash.\\n\\nFeatures:\\n- Made from premium-quality cotton fabric for a soft and comfortable feel\\n- Breathable material for all-day comfort\\n- Easy to care for - simply toss it in the wash and go!\\n- Contains elastane for a non-restrictive, comfortable fit that maintains its shape \\n\\nBenefits:\\n- Versatile - can be dressed up or down for any occasion\\n- Classic white color pairs well with any outfit\\n- Soft and comfortable fabric keeps you feeling good all day long\\n- Stretchy material provides a perfect fit for all body types \\n\\nUpgrade your wardrobe with our Men's White Tshirt today and experience the perfect combination of style and comfort!");

  private String prompt;

  public String getValue() {
    return prompt;
  }
}
