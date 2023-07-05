package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIShortLearningDTO {
  private String role;
  private String content;
}
