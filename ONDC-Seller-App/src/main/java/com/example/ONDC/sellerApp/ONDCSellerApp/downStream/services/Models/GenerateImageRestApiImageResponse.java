package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateImageRestApiImageResponse extends GetHeadersResponse {
  private Long created;
  private Long expires;
  private Result result;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Result {
    private Long created;
    private List<SubResult> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubResult {
      private String url;
    }
  }
}
