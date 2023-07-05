package com.example.ONDC.sellerApp.ONDCSellerApp.controllers;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.ProductControllerConstants.PRODUCT_BASE_URL;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.ProductControllerConstants.SUCCESS;

@Slf4j
@RestController
@RequestMapping(PRODUCT_BASE_URL)
public class ProductController {

  @PostMapping
  public GenericGenerateResponse<String> addProduct(
      @RequestParam(name = "title") String title,
      @RequestParam(name = "description") String description,
      @RequestParam(name = "category") Integer category,
      @RequestParam(name = "additional_description", required = false) String additionalInfo,
      @RequestParam(name = "created_by", required = false) Long createdBy,
      @RequestParam(name = "price") double price,
      @RequestParam(name = "net_quantity", required = false) String netQuantity,
      @RequestParam(name = "images", required = false) List<MultipartFile> images,
      @RequestParam(name = "image_url", required = false) List<String> imageUrl) throws ONDCProductException {
    log.info("[addProduct] Request received | title: {}", title);
    log.info("[addProduct] Request success | title: {}", title);
    return new GenericGenerateResponse<>(SUCCESS);
  }
}
