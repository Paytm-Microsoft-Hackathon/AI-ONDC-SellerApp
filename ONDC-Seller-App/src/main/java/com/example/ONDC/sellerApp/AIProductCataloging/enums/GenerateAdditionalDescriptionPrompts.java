package com.example.ONDC.sellerApp.AIProductCataloging.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GenerateAdditionalDescriptionPrompts {
  GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP("Act like a senior nutritionist to find and list down only the nutrition content table for %s briefly in a positive tone."),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT("peanuts"),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT("Nutrition Content per 100g:\n- Calories: 567 kcal\n- Carbohydrates: 16.1 g\n- Fiber: 8.5 g\n- Sugars: 4.7 g\n- Protein: 25.8 g\n- Fat: 49.2 g\n- Saturated fat: 6.28 g\n- Monounsaturated fat: 24.4 g\n- Polyunsaturated fat: 15.6 g\n- Omega-3 fatty acids: 0 g\n- Omega-6 fatty acids: 15.6 g\n- Vitamin B1 (Thiamine): 0.64 mg\n- Vitamin B2 (Riboflavin): 0.14 mg\n- Vitamin B3 (Niacin): 12.1 mg\n- Vitamin B5 (Pantothenic acid): 1.8 mg\n- Vitamin B6 (Pyridoxine): 0.35 mg\n- Vitamin B9 (Folate): 240 mcg\n- Vitamin E: 8.33 mg\n- Calcium: 92 mg\n- Iron: 4.6 mg\n- Magnesium: 168 mg\n- Phosphorus: 376 mg\n- Potassium: 705 mg\n- Zinc: 3.27 mg"),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT2("paneer"),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT2("Nutrition Content per 100g:\n- Calories: 265 kcal\n- Carbohydrates: 1.2 g\n- Fiber: 0 g\n- Sugars: 1.2 g\n- Protein: 18.3 g\n- Fat: 21.4 g\n- Saturated fat: 13.8 g\n- Monounsaturated fat: 5.4 g\n- Polyunsaturated fat: 0.6 g\n- Omega-3 fatty acids: 0 g\n- Omega-6 fatty acids: 0.6 g\n- Vitamin B2 (Riboflavin): 0.2 mg\n- Vitamin B9 (Folate): 16 mcg\n- Calcium: 208 mg\n- Phosphorus: 138 mg\n- Sodium: 1 mg\n- Potassium: 39 mg\n- Zinc: 0.4 mg"),
  GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP_BEVERAGES("Act like a senior nutritionist to find and list down only the nutrition content table for a serving of %s briefly in a positive tone"),
  GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP_FASHION("Act like a fashion designer and help me figure out how to style a %s briefly in a positive tone"),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT_FASHION("Leather Adda biker mens leather jacket"),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT_FASHION("For styling a Leather Adda biker mens leather jacket, heres how to create a stylish look. \nClassic Cool: Pair the Leather Adda biker jacket with a plain white T-shirt, slim-fit black jeans, and black leather boots." +
    " Complete the look with a black leather belt and aviator sunglasses for a timeless and effortlessly cool style.\nEdgy Attitude: Combine the jacket with a graphic printed tee, ripped black jeans, and chunky black boots. Add some statement accessories like a studded belt, metal chains," +
    " or a leather wristband to exude an edgy and rebellious vibe.\nUrban Chic: Layer the leather jacket over a lightweight knit sweater, dark-colored chinos, and white sneakers. Roll up the sleeves of the jacket slightly for a relaxed and urban-inspired outfit.\nSophisticated Style: Elevate your" +
    " look by wearing the biker jacket with a crisp white button-down shirt, tailored trousers, and suede Chelsea boots. Add a patterned silk pocket square for a touch of refinement and complete the ensemble with a stylish leather briefcase."),
  GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT2_FASHION("To style the Uiriuy Men's Regular Fit Floral Print Cut Away Collar Casual Shirt, here are some fashion tips:\nPair it with solid-colored bottoms: Opt for solid-colored trousers or chinos in neutral tones like navy blue, khaki, " +
    "or beige. This will balance out the vibrant floral print of the shirt.\nKeep it casual: Embrace a relaxed and casual look by wearing the shirt untucked. This will maintain the laid-back vibe of the casual shirt.\nLayer it with a solid-colored jacket: For a stylish touch, layer the shirt with" +
    " a lightweight solid-colored jacket. A denim jacket or a bomber jacket can add a trendy and effortless flair to your outfit.\nRoll up the sleeves: Consider rolling up the sleeves of the shirt to enhance the casual and relaxed appeal. It adds a touch of informality and can be practical during warmer " +
    "weather.\nMinimalist accessories: Keep the focus on the shirt by opting for minimalist accessories. A simple wristwatch or a single bracelet can complement the overall look without overpowering the floral print.\nClassic footwear: Complete the outfit with classic footwear choices such as clean sneakers," +
    " loafers, or boat shoes. These styles will maintain the casual yet refined aesthetic.\nRemember, personal style and preferences may vary, so feel free to adapt these suggestions to your own taste and occasion.");


  private String prompt;

  public String getValue() {
    return prompt;
  }
}
