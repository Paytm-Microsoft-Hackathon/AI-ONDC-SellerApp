package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterAdultContentResponse {
  private FilterAdultContentResponseDetails adult;
  private String requestId;
  private FilterAdultContentResponseMetaData metadata;
  private String modelVersion;
}
