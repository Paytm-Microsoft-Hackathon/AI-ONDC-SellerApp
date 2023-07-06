package com.example.ONDC.sellerApp.AIProductCataloging.controllers;

import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.RateLimitingService;
import com.example.ONDC.sellerApp.AIProductCataloging.exceptions.ONDCProductException;
import com.example.ONDC.sellerApp.AIProductCataloging.model.AddProductRequest;
import com.example.ONDC.sellerApp.AIProductCataloging.model.FetchProductResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.service.ProductService;
import com.example.ONDC.sellerApp.AIProductCataloging.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductException.API_ATTEMPT_EXCEEDED;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.ProductControllerConstants.PRODUCT_BASE_URL;
import static com.example.ONDC.sellerApp.AIProductCataloging.Constants.ProductControllerConstants.SUCCESS;

@Slf4j
@RestController
@RequestMapping(PRODUCT_BASE_URL)
@RequiredArgsConstructor
public class ProductController {

  @Autowired private ProductService productService;
  @Autowired private RateLimitingService rateLimitingService;

  @GetMapping
  public List<FetchProductResponse> getProducts(@RequestParam(name = "offset", defaultValue = "0") int offset,
                                                @RequestParam(name = "limit", defaultValue = "10") int limit)  {
    return productService.getProducts(offset, limit);
  }

  @PostMapping
  public GenericGenerateResponse<String> addProduct(
      @RequestPart(name = "data") String data,
      @RequestPart(name = "images", required = false) List<MultipartFile> images) throws ONDCProductException, IOException {
    if (rateLimitingService.checkForRateLimit(PRODUCT_BASE_URL)) {
      log.warn("[ADD_PRODUCT] Limit the api due to multiple attempts");
      throw new ONDCProductException(API_ATTEMPT_EXCEEDED);
    }
    AddProductRequest request = JsonUtil.parseJson(data, AddProductRequest.class);
    log.info("[addProduct] Request received | request: {}", request);
    productService.addProduct(request, images);
    log.info("[addProduct] Request success | request: {}", request);
    rateLimitingService.increaseCount(PRODUCT_BASE_URL);
    return new GenericGenerateResponse<>(SUCCESS);
  }
}
