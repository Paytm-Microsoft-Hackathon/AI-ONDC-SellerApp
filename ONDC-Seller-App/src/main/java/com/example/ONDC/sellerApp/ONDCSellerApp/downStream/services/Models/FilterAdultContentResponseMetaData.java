package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterAdultContentResponseMetaData {
  private Long height;
  private Long width;
  private String format;
}
