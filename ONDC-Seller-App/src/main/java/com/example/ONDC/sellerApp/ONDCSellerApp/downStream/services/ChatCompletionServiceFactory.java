package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionRequestFlowtype;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatCompletionServiceFactory {

  @Autowired private EnhanceTitleService enhanceTitleService;

  public CommonChatCompletionService getChatCompletionServiceBasedOnFlowtype(
      ChatCompletionRequestFlowtype flowtype) {
    switch (flowtype) {
      case ENHANCE_TITLE :
        return enhanceTitleService;
      case GENERATE_DESCRIPTION :
      case GENERATE_ADDITIONAL_DESCRIPTION :
      case ENHANCE_DESCRIPTION :
      default:
        throw new IllegalArgumentException("Something went wrong, Please try again");
    }
  }
}