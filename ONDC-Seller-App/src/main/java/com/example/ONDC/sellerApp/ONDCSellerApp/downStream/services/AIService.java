package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenerateImageRestApiImageRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenerateImageRestApiImageResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.ImageData;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

  public GenericGenerateResponse<ImageData> generateImage(String title, Integer category) throws ONDCProductException {
    GenerateImageRestApiImageRequest request =
      new GenerateImageRestApiImageRequest(title + ProductCategory.fromValue(category).getName(), imageSize, imageNum);
    Map<String, String> headers = new HashMap<>();
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    GenerateImageRestApiImageResponse imageResponse =
      restTemplateService.executePostRequest(imageUrl, GenerateImageRestApiImageResponse.class, request, headers, params);
    ImageData responseData = new ImageData();
    List<String> imageList = new ArrayList<>();
    if(Objects.nonNull(imageResponse) && Objects.nonNull(imageResponse.getData())) {
      imageResponse.getData().forEach(generatedImage -> imageList.add(generatedImage.getUrl()));
    }
    responseData.setImage(imageList);
    return new GenericGenerateResponse<>(responseData);
  }
}
