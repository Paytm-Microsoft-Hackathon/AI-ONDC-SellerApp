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
public class AddProductRequest {

  private String title;
  private String description;
  private Integer category;

  @JsonProperty("additional_description")
  private  String additionalInfo;

  @JsonProperty("created_by")
  private String createdBy;

  @JsonProperty("price")
  private double price;

  @JsonProperty("net_quantity")
  private String netQuantity;

  @JsonProperty("images")
  private List<String> imageUrl;
}
