package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services;

import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.AIChatCompletionRequest;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.ChatCompletionResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.FilterAdultContentResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.GenerateImageRestApiImageRequest;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.GenerateImageRestApiImageResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.GetHeadersResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.ImageData;
import com.example.ONDC.sellerApp.AIProductCataloging.enums.ImageGenerationPrompt;
import com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductCategory;
import com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductException;
import com.example.ONDC.sellerApp.AIProductCataloging.exceptions.ONDCProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.*;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.API_KEY;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.API_VERSION;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.APPLICATION_JSON;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.CHAT_COMPLETION_API_VERSION;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.CONTENT_TYPE;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.OPEN_AI_IMAGE_REDIRECTION_URL;
import static com.example.ONDC.sellerApp.AIProductCataloging.enums.AzureEndpoints.CHAT_COMPLETION;
import static com.example.ONDC.sellerApp.AIProductCataloging.enums.AzureEndpoints.FILTER_ADULT_CONTENT;

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

  public GenericGenerateResponse<ImageData> generateImage(String title, Integer category) throws ONDCProductException, InterruptedException {
    ProductCategory productCategory = ProductCategory.fromValue(category);
    String prompt = Objects.nonNull(productCategory)
      ? ImageGenerationPrompt.fromCategory(productCategory)
      .getPromptMessage().replace("%s", title) : title;

    GenerateImageRestApiImageRequest request =
      new GenerateImageRestApiImageRequest(prompt, imageSize, imageNum);
    Map<String, String> headers = new HashMap<>();
    headers.put(API_KEY, apiKey);

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.put(API_VERSION, Collections.singletonList(apiVersion));
    HttpHeaders imageHeaders =
      restTemplateService.executePostRequest(imageUrl, GetHeadersResponse.class, request, headers, params);

    String openAIImageURL = Objects.requireNonNull(imageHeaders.get(OPEN_AI_IMAGE_REDIRECTION_URL)).toString();
    String url = openAIImageURL.substring(1, openAIImageURL.length() - 1);
    Thread.sleep(5000);

    GenerateImageRestApiImageResponse responseData =
      restTemplateService.executeGetRequest(
        url,
        GenerateImageRestApiImageResponse.class,
        headers, params);

    List<String> imageUrlList = new ArrayList<>();
    responseData.getResult().getData().forEach(imageUrl -> imageUrlList.add(imageUrl.getUrl()));
    return new GenericGenerateResponse<>(new ImageData(imageUrlList));
  }

  public Resource removeBackGround(MultipartFile file) throws ONDCProductException, IOException {
    Map<String, String> headers = new HashMap<>();
    headers.put(X_API_KEY, "c5f5c317297de2f9d78c3addb7d157f9982b82291dfa761a9fb597d4ee6a5e52b67f6f923ad8813e9acff9a1998694ae");
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    return restTemplateService.executePostRequestWithImage("https://clipdrop-api.co/remove-background/v1", MultipartFile.class, file, headers, params);
  }

  public ChatCompletionResponse chatCompletionAI(AIChatCompletionRequest request) throws ONDCProductException {
    Map<String, String> headers = new HashMap<>();
    headers.put(API_KEY, apiKey);
    headers.put(CONTENT_TYPE, APPLICATION_JSON);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.put(API_VERSION, Collections.singletonList(CHAT_COMPLETION_API_VERSION));
    return
        restTemplateService.executePostRequestV3(
            CHAT_COMPLETION.getValue(), ChatCompletionResponse.class, request, headers, params);
  }

  public FilterAdultContentResponse filterAdultContent(String filepath) throws ONDCProductException {
      Map<String, String> headers = new HashMap<>();
      headers.put(FILTER_ADULT_CONTENT_KEY_HEADER, FILTER_ADULT_CONTENT_KEY);
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.put(VISUAL_FEATURES, Collections.singletonList(CHECK_ADULT));
      MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
      try (FileInputStream fileInputStream = new FileInputStream(filepath)) {
        request.add("file", new InputStreamResource(fileInputStream));
        return restTemplateService.executePostRequestV3(
            FILTER_ADULT_CONTENT.getValue(), FilterAdultContentResponse.class, request, headers, params);
      } catch (Exception ex) {
        log.error("[filterAdultContent] Error occurred in api: {} | filepath: {}", FILTER_ADULT_CONTENT.getValue(), filepath, ex);
        throw new ONDCProductException(ProductException.SOMETHING_WENT_WRONG);
      }
  }
}
