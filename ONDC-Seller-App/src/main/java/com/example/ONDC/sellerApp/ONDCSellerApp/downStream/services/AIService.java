package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.*;

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

  public MultipartFile removeBackGround(MultipartFile file) throws ONDCProductException, IOException {
    Map<String, String> headers = new HashMap<>();
    headers.put(X_API_KEY, "c5f5c317297de2f9d78c3addb7d157f9982b82291dfa761a9fb597d4ee6a5e52b67f6f923ad8813e9acff9a1998694ae");
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    //params.put(API_VERSION, Collections.singletonList("c5f5c317297de2f9d78c3addb7d157f9982b82291dfa761a9fb597d4ee6a5e52b67f6f923ad8813e9acff9a1998694ae"));
    MultipartFile response =
      restTemplateService.executePostRequestWithImage("https://clipdrop-api.co/remove-background/v1", MultipartFile.class, file, headers, params);
    return  response;
  }

}
