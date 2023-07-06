package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIShortLearningDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
  public AIChatCompletionRequest getChatCompletionRequest(String description, int tokenSize,Integer category) {
    return AIChatCompletionRequest.builder()
      .maxTokens(tokenSize)
      .messages(
        Arrays.asList(
          AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_DESCRIPTION_TONALITY_SETUP.getValue()).build(),
          AIShortLearningDTO.builder().role(USER_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_INPUT.getValue()).build(),
          AIShortLearningDTO.builder().role(SYSTEM_ROLE).content(ENHANCE_DESCRIPTION_SHORT_LEARNING_OUTPUT.getValue()).build(),
          AIShortLearningDTO.builder().role(USER_ROLE).content(description).build()
        )
      )
      .temperature(FIXED_TEMPERATURE)
      .topP(FIXED_TOP_P)
      .build();
  }

}
