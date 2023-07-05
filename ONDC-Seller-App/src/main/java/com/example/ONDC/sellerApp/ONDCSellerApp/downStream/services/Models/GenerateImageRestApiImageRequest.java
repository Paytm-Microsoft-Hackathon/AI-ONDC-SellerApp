package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateImageRestApiImageRequest {
  private String prompt;
  private String size;
  @JsonProperty("num_images")
  private int numImages;
}
