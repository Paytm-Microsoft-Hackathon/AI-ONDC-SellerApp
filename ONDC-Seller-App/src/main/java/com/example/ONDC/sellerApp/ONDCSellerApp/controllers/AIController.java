package com.example.ONDC.sellerApp.ONDCSellerApp.controllers;

import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.AIService;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.GenericGenerateResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.ImageData;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.example.ONDC.sellerApp.ONDCSellerApp.constants.*;

@Slf4j
@RestController
@RequestMapping(BASE_URL)
public class AIController {
  @Autowired private AIService aiService;

  @GetMapping(GENERATE_IMAGE)
  public GenericGenerateResponse<ImageData> generateImage(
    @RequestParam(name = "title") String title,
    @RequestParam(name = "category") Integer category) throws ONDCProductException {
    return aiService.generateImage(title, category);
  }


  @PostMapping(REMOVE_BACK_GROUND)
  public MultipartFile generateImage(@RequestPart MultipartFile file) throws ONDCProductException, IOException {
    return aiService.removeBackGround(file);
  }

}
