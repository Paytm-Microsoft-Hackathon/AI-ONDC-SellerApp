package com.example.ONDC.sellerApp.ONDCSellerApp.controllers;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.AIService;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.ChatCompletionServiceFactory;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.CommonDescriptionResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.ImageData;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.RateLimitingService;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ChatCompletionRequestFlowtype;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.*;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.BASE_URL;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.ENHANCE_TITLE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.GENERATE_ADDITIONAL_DESCRIPTION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.GENERATE_DESCRIPTION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.GENERATE_IMAGE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException.API_ATTEMPT_EXCEEDED;

@Slf4j
@RestController
@RequestMapping(BASE_URL)
public class AIController {

  @Autowired private AIService aiService;
  @Autowired private ChatCompletionServiceFactory chatCompletionServiceFactory;
  @Autowired private RateLimitingService rateLimitingService;

  @GetMapping(GENERATE_IMAGE)
  public GenericGenerateResponse<ImageData> generateImage(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category) throws ONDCProductException, InterruptedException {
    if(rateLimitingService.checkForRateLimit(GENERATE_IMAGE)) {
      log.warn("[GENERATE_IMAGE] Limit the api due to multiple attempts");
      throw new ONDCProductException(API_ATTEMPT_EXCEEDED);
    }
    GenericGenerateResponse<ImageData> response = aiService.generateImage(title, category);
    rateLimitingService.increaseCount(GENERATE_IMAGE);
    return response;
  }

  @PostMapping(REMOVE_BACKGROUND)
  public Resource generateImage(@RequestPart List<MultipartFile> file) throws ONDCProductException, IOException {
    if(rateLimitingService.checkForRateLimit(GENERATE_IMAGE)) {
      log.warn("[GENERATE_DESCRIPTION] Limit the api due to multiple attempts");
      throw new ONDCProductException(API_ATTEMPT_EXCEEDED);
    }
    Resource response = aiService.removeBackGround(file.get(0));
    rateLimitingService.increaseCount(GENERATE_IMAGE);
    return response;
  }

  @GetMapping(ENHANCE_TITLE)
  public GenericGenerateResponse<CommonDescriptionResponse> enhanceTitle(
      @RequestParam(name = "title") String title,
      @RequestParam(name = "category") Integer category) throws ONDCProductException {
    log.info("[enhanceDescription] Request title: {}", title);
    if(rateLimitingService.checkForRateLimit(ENHANCE_TITLE)) {
      log.warn("[ENHANCE_TITLE] Limit the api due to multiple attempts");
      throw new ONDCProductException(API_ATTEMPT_EXCEEDED);
    }
    GenericGenerateResponse<CommonDescriptionResponse> response =
        chatCompletionServiceFactory
            .getChatCompletionServiceBasedOnFlowtype(ChatCompletionRequestFlowtype.ENHANCE_TITLE)
            .getChatCompletionRecommendation(null, category, title);
    log.info("[enhanceDescription] Response: {}", response);
    rateLimitingService.increaseCount(ENHANCE_TITLE);
    return  response;
  }

  @GetMapping(ENHANCE_DESCRIPTION)
  public GenericGenerateResponse<CommonDescriptionResponse> enhanceDescription(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category,
    @RequestParam(name = "description") String description) throws ONDCProductException {
    log.info("[enhanceDescription] Request title: {},category :{}, description :{}", title,category,description);
    if(rateLimitingService.checkForRateLimit(ENHANCE_DESCRIPTION)) {
      log.warn("[ENHANCE_DESCRIPTION] Limit the api due to multiple attempts");
      throw new ONDCProductException(API_ATTEMPT_EXCEEDED);
    }
    GenericGenerateResponse<CommonDescriptionResponse> response =
      chatCompletionServiceFactory
        .getChatCompletionServiceBasedOnFlowtype(ChatCompletionRequestFlowtype.ENHANCE_DESCRIPTION)
        .getChatCompletionRecommendation(description, category, title);
    log.info("[enhanceDescription] Response: {}", response);
    rateLimitingService.increaseCount(ENHANCE_DESCRIPTION);
    return  response;
  }

  @GetMapping(GENERATE_DESCRIPTION)
  public GenericGenerateResponse<CommonDescriptionResponse> generateDescription(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category) throws ONDCProductException {
    if(rateLimitingService.checkForRateLimit(GENERATE_DESCRIPTION)) {
      log.warn("[GENERATE_DESCRIPTION] Limit the api due to multiple attempts");
      throw new ONDCProductException(API_ATTEMPT_EXCEEDED);
    }
    GenericGenerateResponse<CommonDescriptionResponse> response =
      chatCompletionServiceFactory
        .getChatCompletionServiceBasedOnFlowtype(ChatCompletionRequestFlowtype.GENERATE_DESCRIPTION)
        .getChatCompletionRecommendation(null, category, title);
    log.info("[generateDescription] Response: {}", response);
    rateLimitingService.increaseCount(GENERATE_DESCRIPTION);
    return response;
  }

  @GetMapping(GENERATE_ADDITIONAL_DESCRIPTION)
  public GenericGenerateResponse<CommonDescriptionResponse> generateAdditionalDescription(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category) throws ONDCProductException {
    if(rateLimitingService.checkForRateLimit(GENERATE_ADDITIONAL_DESCRIPTION)) {
      log.warn("[GENERATE_ADDITIONAL_DESCRIPTION] Limit the api due to multiple attempts");
      throw new ONDCProductException(API_ATTEMPT_EXCEEDED);
    }
    GenericGenerateResponse<CommonDescriptionResponse> response =
      chatCompletionServiceFactory
        .getChatCompletionServiceBasedOnFlowtype(ChatCompletionRequestFlowtype.GENERATE_ADDITIONAL_DESCRIPTION)
        .getChatCompletionRecommendation(null, category, title);
    log.info("[generateAdditionalDescription] Response: {}", response);
    rateLimitingService.increaseCount(GENERATE_ADDITIONAL_DESCRIPTION);
    return response;
  }
}
