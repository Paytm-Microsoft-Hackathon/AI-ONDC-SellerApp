package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services;

import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.ChatCompletionResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.CommonDescriptionResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
public abstract class CommonChatCompletionService {

  @Autowired private AIService aiService;

  abstract int getMaxTokenSize();
  abstract AIChatCompletionRequest getChatCompletionRequest(String description , int tokenSize, Integer category, String title);

  public GenericGenerateResponse<CommonDescriptionResponse> getChatCompletionRecommendation(String description, Integer category, String title) throws ONDCProductException {
    AIChatCompletionRequest request = getChatCompletionRequest(description, getMaxTokenSize(), category,title);
    ChatCompletionResponse response = aiService.chatCompletionAI(request);
    if (Objects.nonNull(response)
      && !CollectionUtils.isEmpty(response.getChoices())
      && Objects.nonNull(response.getChoices().get(0).getMessage())
      && StringUtils.hasLength(response.getChoices().get(0).getMessage().getContent())) {
      return new GenericGenerateResponse<>(new CommonDescriptionResponse(response.getChoices().get(0).getMessage().getContent()));
    } else {
      return new GenericGenerateResponse<>(new CommonDescriptionResponse(description));
    }
  }
}
