package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import lombok.Data;

import java.util.List;

@Data
public class GenerateImageRestApiImageResponse {
  private List<GeneratedImage> data;

  @Data
  public static class GeneratedImage {
    private String url;
  }
}
