package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIShortLearningDTO;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.CATEGORY;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.SYSTEM_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.USER_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.TITLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateDescriptionPrompts.GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateDescriptionPrompts.GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateDescriptionPrompts.GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT_FASHION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateDescriptionPrompts.GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT_FOOD;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateDescriptionPrompts.GENERATE_DESCRIPTION_TONALITY_SETUP;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateDescriptionPrompts.GENERATE_DESCRIPTION_TONALITY_SETUP_FASHION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.GenerateDescriptionPrompts.GENERATE_DESCRIPTION_TONALITY_SETUP_FOOD;

@Slf4j
@Service
public class GenerateDescriptionService extends CommonChatCompletionService {
  @Override
  public int getMaxTokenSize() {
    return 600;
  }

  @Override
  public AIChatCompletionRequest getChatCompletionRequest(String description, int tokenSize, Integer category,String title) {
    return AIChatCompletionRequest.builder()
      .maxTokens(tokenSize)
      .messages(getMessageList(description, category))
      .build();
  }

  private List<AIShortLearningDTO> getMessageList(String description, Integer category) {
    String productCategory = Objects.nonNull(ProductCategory.fromValue(category)) ? ProductCategory.fromValue(category).getName() : "";
    return switch (category) {
      case 1, 2, 3, 5 -> Arrays.asList(
        AIShortLearningDTO.builder()
          .role(SYSTEM_ROLE).content(GENERATE_DESCRIPTION_TONALITY_SETUP.getValue()
            .replace(TITLE, description).replace(CATEGORY, productCategory)).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build());

      case 4 -> Arrays.asList(
        AIShortLearningDTO.builder()
          .role(SYSTEM_ROLE).content(GENERATE_DESCRIPTION_TONALITY_SETUP_FOOD.getValue()
            .replace(TITLE, description)).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT_FOOD.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build());

      case 6 -> Arrays.asList(
        AIShortLearningDTO.builder()
          .role(SYSTEM_ROLE).content(GENERATE_DESCRIPTION_TONALITY_SETUP_FASHION.getValue()
            .replace(TITLE, description)).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
        AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_DESCRIPTION_SHORT_LEARNING_OUTPUT_FASHION.getValue()).build(),
        AIShortLearningDTO.builder().role(USER_ROLE).content(description).build());

      default -> throw new IllegalArgumentException("Something went wrong, Please try again");
    };
  }

}
