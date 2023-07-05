package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.ChatCompletionResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.CommonDescriptionResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenerateImageRestApiImageRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenerateImageRestApiImageResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GetHeadersResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.ImageData;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.API_KEY;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.API_VERSION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.APPLICATION_JSON;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.CHAT_COMPLETION_API_VERSION;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.CONTENT_TYPE;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.OPEN_AI_IMAGE_REDIRECTION_URL;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.AzureEndpoints.CHAT_COMPLETION;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIService {
  private final RestTemplateService restTemplateService;
  @Value("${openai.image-generator.api-key}")
  private String apiKey;
  @Value("${openai.image-generator.url}")
  private String imageUrl;
  @Value("${openai.image-size}")
  private String imageSize;
  @Value("${openai.image-number}")
  private int imageNum;
  @Value("${openai.api-version}")
  private String apiVersion;

  public GenericGenerateResponse<ImageData> generateImage(String title, Integer category) throws ONDCProductException {
    GenerateImageRestApiImageRequest request =
      new GenerateImageRestApiImageRequest(title + ProductCategory.fromValue(category).getName(), imageSize, imageNum);
    Map<String, String> headers = new HashMap<>();
    headers.put(API_KEY, apiKey);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.put(API_VERSION, Collections.singletonList(apiVersion));
    HttpHeaders imageHeaders =
      restTemplateService.executePostRequest(imageUrl, GetHeadersResponse.class, request, headers, params);
    String openAIImageURL = imageHeaders.get(OPEN_AI_IMAGE_REDIRECTION_URL).toString();
    String url = openAIImageURL.substring(1, openAIImageURL.length() - 1);
    GenerateImageRestApiImageResponse responseData =
      restTemplateService.executeGetRequest(
        url,
        GenerateImageRestApiImageResponse.class,
        headers, params);
    log.info("responseData: {}", responseData);
    List<String> imageUrlList = new ArrayList<>();
    responseData.getResult().getData().forEach(imageUrl -> imageUrlList.add(imageUrl.getUrl()));
    return new GenericGenerateResponse<>(new ImageData(imageUrlList));
  }

  public ChatCompletionResponse chatCompletionAI(AIChatCompletionRequest request) throws ONDCProductException {
    Map<String, String> headers = new HashMap<>();
    headers.put(API_KEY, apiKey);
    headers.put(CONTENT_TYPE, APPLICATION_JSON);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.put(API_VERSION, Collections.singletonList(CHAT_COMPLETION_API_VERSION));
    return
        restTemplateService.executePostRequestV2(
            CHAT_COMPLETION.getValue(), ChatCompletionResponse.class, request, headers, params);
  }
}
