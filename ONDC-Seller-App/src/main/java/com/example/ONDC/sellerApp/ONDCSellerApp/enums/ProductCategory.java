package com.example.ONDC.sellerApp.ONDCSellerApp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductCategory {
  GROCERY(1, "Grocery"),
  PACKED_COMMODITIES(2, "Packaged Commodities"),
  PACKED_FOOD(3, "Packaged Foods"),
  FRUITS_AND_VEGETABLES(4, "Fruits and Vegetables"),
  F_AND_B(5, "F&B"),
  FASHION(6, "Fashion");
  private final int value;
  private String name;

  ProductCategory(int value, String name) {
    this.value = value;
    this.name = name;
  }

  @JsonCreator
  public static ProductCategory fromValue(int value) {
    return switch (value) {
      case 1 -> GROCERY;
      case 2 -> PACKED_COMMODITIES;
      case 3 -> PACKED_FOOD;
      case 4 -> FRUITS_AND_VEGETABLES;
      case 5 -> F_AND_B;
      case 6 -> FASHION;
      default -> null;
    };
  }

  @JsonValue
  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }
}
