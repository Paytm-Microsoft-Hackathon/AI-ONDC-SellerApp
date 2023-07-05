package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.CommonDescriptionResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenericGenerateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class CommonChatCompletionService {

  @Autowired private AIService aiService;

  abstract int getMaxTokenSize();
  abstract AIChatCompletionRequest getChatCompletionRequest(String description , int tokenSize);

  public GenericGenerateResponse<CommonDescriptionResponse> getChatCompletionRecommendation(String description) {
    AIChatCompletionRequest request = getChatCompletionRequest(description, getMaxTokenSize());
    // This method can be used
    // Common api call to be made after making api
    return new GenericGenerateResponse<>(new CommonDescriptionResponse());
  }
}
