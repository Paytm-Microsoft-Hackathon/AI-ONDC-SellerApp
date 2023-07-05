package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionRequestFlowtype;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatCompletionServiceFactory {

  @Autowired private EnhanceTitleService enhanceTitleService;
  @Autowired private GenerateDescriptionService generateDescriptionService;
  @Autowired private GenerateAdditionalDescriptionService generateAdditionalDescriptionService;

  public CommonChatCompletionService getChatCompletionServiceBasedOnFlowtype(
      ChatCompletionRequestFlowtype flowtype) {
    return switch (flowtype) {
      case ENHANCE_TITLE -> enhanceTitleService;
      case GENERATE_DESCRIPTION -> generateDescriptionService;
      case GENERATE_ADDITIONAL_DESCRIPTION -> generateAdditionalDescriptionService;
      // case ENHANCE_DESCRIPTION :
      default -> throw new IllegalArgumentException("Something went wrong, Please try again");
    };
  }
}
