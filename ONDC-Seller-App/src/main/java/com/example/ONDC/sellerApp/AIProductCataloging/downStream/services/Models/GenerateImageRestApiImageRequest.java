package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models;

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
  @JsonProperty("n")
  private int numImages;
}
