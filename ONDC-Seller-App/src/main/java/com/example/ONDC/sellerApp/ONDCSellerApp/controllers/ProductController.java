package com.example.ONDC.sellerApp.ONDCSellerApp.controllers;

import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.ProductControllerConstants.PRODUCT_BASE_URL;

@Slf4j
@RestController
@RequestMapping(PRODUCT_BASE_URL)
public class ProductController {

  @PostMapping
  public void generateImage(
      @RequestParam(name = "title") String title,
      @RequestParam(name = "category") Integer category) throws ONDCProductException {
  }
}
