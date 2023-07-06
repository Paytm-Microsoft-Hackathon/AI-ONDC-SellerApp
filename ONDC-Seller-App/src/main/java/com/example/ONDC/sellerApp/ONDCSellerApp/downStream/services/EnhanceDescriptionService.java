package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIShortLearningDTO;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.FIXED_TEMPERATURE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.FIXED_TOP_P;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.SYSTEM_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.USER_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionPrompts.*;

@Service
@Slf4j
public class EnhanceDescriptionService extends CommonChatCompletionService{

  @Override
  public int getMaxTokenSize() {
    return 100;
  }

  @Override
  public AIChatCompletionRequest getChatCompletionRequest(String description, int tokenSize,Integer category,String title) {

    return AIChatCompletionRequest.builder()
      .maxTokens(tokenSize)
      .messages(getMessageList(description, category, title)
      )
      .temperature(FIXED_TEMPERATURE)
      .topP(FIXED_TOP_P)
      .build();
  }

  private List<AIShortLearningDTO> getMessageList(String description, Integer category, String title) {
    return switch (category) {
      case 1, 2, 3, 5, 6 -> Arrays.asList(
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(String.format(ENHANCE_DESCRIPTION_TONALITY_SETUP_COMMON.getValue(),description,ProductCategory.fromValue(category).getName(),title)).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_COMMON_INPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_COMMON_OUTPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build()
      );

      case 4 -> Arrays.asList(
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(String.format(ENHANCE_DESCRIPTION_TONALITY_SETUP_FOOD.getValue(),description,ProductCategory.fromValue(category).getName(),title)).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_INPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_OUTPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_INPUT_V2.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_FOOD_OUTPUT_V2.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build()
      );

      default -> throw new IllegalArgumentException("Something went wrong, Please try again");
    };
  }
}
