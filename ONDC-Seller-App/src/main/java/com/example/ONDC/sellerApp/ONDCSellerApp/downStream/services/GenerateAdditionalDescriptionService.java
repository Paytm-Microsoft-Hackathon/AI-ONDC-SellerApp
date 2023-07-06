package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIShortLearningDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.FIXED_TEMPERATURE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.FIXED_TOP_P;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.SYSTEM_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.USER_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT2;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT_FASHION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT2;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT2_FASHION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT_FASHION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP_BEVERAGES;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateAdditionalDescriptionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP_FASHION;

@Slf4j
@Service
public class GenerateAdditionalDescriptionService extends CommonChatCompletionService {
  @Override
  public int getMaxTokenSize() {
    return 600;
  }

  @Override
  public AIChatCompletionRequest getChatCompletionRequest(String description, int tokenSize, Integer category, String title) {
    return AIChatCompletionRequest.builder()
      .maxTokens(tokenSize)
      .messages(getMessageList(title, category))
      .temperature(FIXED_TEMPERATURE)
      .topP(FIXED_TOP_P)
      .build();
  }

  private List<AIShortLearningDTO> getMessageList(String description, Integer category) {
    return switch (category) {
      case 1, 2, 3 -> Arrays.asList(
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT2.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT2.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build());

      case 5 -> Arrays.asList(
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP_BEVERAGES.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT2.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT2.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build());

      case 6 -> Arrays.asList(
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP_FASHION.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT_FASHION.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT_FASHION.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT2.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT2_FASHION.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build());

      default -> throw new IllegalArgumentException("Something went wrong, Please try again");
    };
  }
}
