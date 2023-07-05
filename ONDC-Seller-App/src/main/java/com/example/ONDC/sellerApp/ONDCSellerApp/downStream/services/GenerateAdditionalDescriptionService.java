package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIShortLearningDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.SYSTEM_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.PromtGenerationConstants.USER_ROLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT_V2;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionPrompts.GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionPrompts.GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT;

@Slf4j
@Service
public class GenerateAdditionalDescriptionService extends CommonChatCompletionService {
  @Override
  public int getMaxTokenSize() {
    return 600;
  }

  @Override
  public AIChatCompletionRequest getChatCompletionRequest(String description, int tokenSize, Integer category) {
    return AIChatCompletionRequest.builder()
      .maxTokens(tokenSize)
      .messages(
        Arrays.asList(
          AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_TONALITY_SETUP.getValue()).build(),
          AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
          AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT.getValue()).build(),
          AIShortLearningDTO.builder().role(USER_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
          AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(GENERATE_ADDITIONAL_DESCRIPTION_SHORT_LEARNING_OUTPUT_V2.getValue()).build(),
          AIShortLearningDTO.builder().role(USER_ROLE).content(description).build()
        )
      )
      .build();
  }
}
