package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIShortLearningDTO;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.FIXED_TEMPERATURE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.FIXED_TOP_P;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.OTHER_PRODUCT_CATEGORY;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.SYSTEM_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.USER_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionPrompts.*;

@Slf4j
@Service
public class EnhanceTitleService extends CommonChatCompletionService {

  @Override
  public int getMaxTokenSize() {
    return 15;
  }

  @Override
  public AIChatCompletionRequest getChatCompletionRequest(String description, int tokenSize, Integer category, String title) {
    String productCategory =
        Objects.nonNull(ProductCategory.fromValue(category))
            ? ProductCategory.fromValue(category).getName() : OTHER_PRODUCT_CATEGORY;
    return AIChatCompletionRequest.builder()
        .maxTokens(tokenSize)
        .messages(
            Arrays.asList(
                AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(String.format(ENHANCE_TITLE_TONALITY_SETUP.getValue(), title, productCategory)).build(),
                AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_INPUT.getValue()).build(),
                AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_OUTPUT.getValue()).build(),
                AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_INPUT_V2.getValue()).build(),
                AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_OUTPUT_V2.getValue()).build(),
                AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_INPUT_V3.getValue()).build(),
                AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_OUTPUT_V3.getValue()).build(),
                AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_INPUT_V4.getValue()).build(),
                AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_TITLE_SHORT_LEARNING_OUTPUT_V4.getValue()).build(),
                AIShortLearningDTO.builder().role(USER_ROLE).content(title).build()
                )
        )
        .temperature(FIXED_TEMPERATURE)
        .topP(FIXED_TOP_P)
        .build();
  }
}
