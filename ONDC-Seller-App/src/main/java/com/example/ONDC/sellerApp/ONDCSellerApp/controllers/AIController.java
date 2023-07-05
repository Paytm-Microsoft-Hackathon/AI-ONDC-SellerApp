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
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.*;

@Slf4j
@RestController
@RequestMapping(BASE_URL)
public class AIController {

  @Autowired private AIService aiService;
  @Autowired private ChatCompletionServiceFactory chatCompletionServiceFactory;

  @GetMapping(GENERATE_IMAGE)
  public GenericGenerateResponse<ImageData> generateImage(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category) throws ONDCProductException {
    return aiService.generateImage(title, category);
  }

  @PostMapping(REMOVE_BACKGROUND)
  public Resource generateImage(@RequestPart List<MultipartFile> file) throws ONDCProductException, IOException {
    return aiService.removeBackGround(file.get(2));
  }

  @GetMapping(ENHANCE_TITLE)
  public GenericGenerateResponse<CommonDescriptionResponse> enhanceTitle(
      @RequestParam(name = "title") String title) throws ONDCProductException {
    log.info("[enhanceDescription] Request title: {}", title);
    GenericGenerateResponse<CommonDescriptionResponse> response =
        chatCompletionServiceFactory
            .getChatCompletionServiceBasedOnFlowtype(ChatCompletionRequestFlowtype.ENHANCE_TITLE)
            .getChatCompletionRecommendation(title);
    log.info("[enhanceDescription] Response: {}", response);
    return  response;
  }

  @GetMapping(ENHANCE_DESCRIPTION)
  public GenericGenerateResponse<CommonDescriptionResponse> enhanceDescription(
    @RequestParam(name = "title") String title) throws ONDCProductException {
    log.info("[enhanceDescription] Request title: {}", title);
    GenericGenerateResponse<CommonDescriptionResponse> response =
      chatCompletionServiceFactory
        .getChatCompletionServiceBasedOnFlowtype(ChatCompletionRequestFlowtype.ENHANCE_DESCRIPTION)
        .getChatCompletionRecommendation(title);
    log.info("[enhanceDescription] Response: {}", response);
    return  response;
  }

}
