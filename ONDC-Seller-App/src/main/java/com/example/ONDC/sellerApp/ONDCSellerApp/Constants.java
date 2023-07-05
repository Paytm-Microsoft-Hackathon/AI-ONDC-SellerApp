package com.example.ONDC.sellerApp.ONDCSellerApp;

public class Constants {
  public static final String BASE_URL = "/api/v1/ai";
  public static final String GENERATE_IMAGE = "/generate/image";
  public static final String OPEN_AI_IMAGE_REDIRECTION_URL = "operation-location";
  public static final String API_KEY = "api-key";
  public static final String API_VERSION = "api-version";
  public static final String ENHANCE_TITLE = "/enhance/title";
  public static final String CHAT_COMPLETION_API_VERSION = "2023-03-15-preview";
  public static final String CONTENT_TYPE = "Content-Type";
  public static final String APPLICATION_JSON = "application/json";
  public static final String GENERATE_DESCRIPTION = "/generate/description";

  public static class ProductControllerConstants {
    public static final String PRODUCT_BASE_URL = "/api/v1/product";
    public static final String SUCCESS = "SUCCESS";
  }

  public static class PromtGenerationConstants {
    public static final String USER_ROLE = "user";
    public static final String SYSTEM_ROLE = "system";
  }
}
