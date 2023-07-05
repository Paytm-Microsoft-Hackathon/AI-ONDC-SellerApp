package com.example.ONDC.sellerApp.ONDCSellerApp.controllers;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.AIService;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.ChatCompletionServiceFactory;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.CommonDescriptionResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.ImageData;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionRequestFlowtype;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.BASE_URL;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.ENHANCE_TITLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.GENERATE_DESCRIPTION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.GENERATE_IMAGE;

@Slf4j
@RestController
@RequestMapping(BASE_URL)
public class AIController {

  @Autowired private AIService aiService;
  @Autowired private ChatCompletionServiceFactory chatCompletionServiceFactory;

  @GetMapping(GENERATE_IMAGE)
  public GenericGenerateResponse<ImageData> generateImage(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category) throws ONDCProductException, InterruptedException {
    GenericGenerateResponse<ImageData> response = aiService.generateImage(title, category);
    return response;
  }

  @GetMapping(ENHANCE_TITLE)
  public GenericGenerateResponse<CommonDescriptionResponse> enhanceDescription(
      @RequestParam(name = "title") String title) throws ONDCProductException {
    log.info("[enhanceDescription] Request title: {}", title);
    GenericGenerateResponse<CommonDescriptionResponse> response =
        chatCompletionServiceFactory
            .getChatCompletionServiceBasedOnFlowtype(ChatCompletionRequestFlowtype.ENHANCE_TITLE)
            .getChatCompletionRecommendation(title);
    log.info("[enhanceDescription] Response: {}", response);
    return  response;
  }

  @GetMapping(GENERATE_DESCRIPTION)
  public GenericGenerateResponse<CommonDescriptionResponse> generateDescription(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category) {
    CommonDescriptionResponse response = aiService.generateDescription(title, category);
    return new GenericGenerateResponse<>(response);
  }
}
