package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterAdultContentResponseDetails {
  private Boolean isAdultContent;
  private Boolean isRacyContent;
  private Boolean isGoryContent;
  private Double adultScore;
  private Double racyScore;
  private Double goreScore;
}
