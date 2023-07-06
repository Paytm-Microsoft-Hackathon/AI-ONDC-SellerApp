package com.example.ONDC.sellerApp.AIProductCataloging.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductCategory {
  GROCERY(1, "Grocery"),
  PACKED_ITEMS(2, "Packaged Item"),
  FRUITS_AND_VEGETABLES(3, "Fruits and Vegetables"),
  FOOD(4, "Food"),
  BEVERAGES(5, "Beverages"),
  FASHION(6, "Fashion");
  private final int value;
  private final String name;

  ProductCategory(int value, String name) {
    this.value = value;
    this.name = name;
  }

  @JsonCreator
  public static ProductCategory fromValue(int value) {
    return switch (value) {
      case 1 -> GROCERY;
      case 2 -> PACKED_ITEMS;
      case 3 -> FRUITS_AND_VEGETABLES;
      case 4 -> FOOD;
      case 5 -> BEVERAGES;
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
