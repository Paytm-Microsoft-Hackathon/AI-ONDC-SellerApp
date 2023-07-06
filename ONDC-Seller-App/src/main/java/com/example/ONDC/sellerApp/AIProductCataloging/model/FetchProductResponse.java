package com.example.ONDC.sellerApp.AIProductCataloging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FetchProductResponse {
  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("category")
  private Integer productCategory;

  @JsonProperty("additional_description")
  private String additionalDescription;

  @JsonProperty("created_by")
  private String createdBy;

  @JsonProperty("price")
  private double price;

  @JsonProperty("net_quantity")
  private String netQuantity;

  @JsonProperty("images")
  private List<String> imageUrls;

  @JsonProperty("images_stream")
  private List<byte[]> imageStream;
}
