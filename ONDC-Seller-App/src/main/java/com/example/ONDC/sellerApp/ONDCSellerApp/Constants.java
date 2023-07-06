package com.example.ONDC.sellerApp.ONDCSellerApp;

public class Constants {
  public static final String BASE_URL = "/api/v1/ai";
  public static final String GENERATE_IMAGE = "/generate/image";
  public static final String REMOVE_BACKGROUND = "/remove/background";
  public static final String OPEN_AI_IMAGE_REDIRECTION_URL = "operation-location";
  public static final String API_KEY = "api-key";
  public static final String X_API_KEY = "x-api-key";
  public static final String API_VERSION = "api-version";
  public static final String ENHANCE_TITLE = "/enhance/title";
  public static final String CHAT_COMPLETION_API_VERSION = "2023-03-15-preview";
  public static final String CONTENT_TYPE = "Content-Type";
  public static final String APPLICATION_JSON = "application/json";
  public static final String GENERATE_DESCRIPTION = "/generate/description";
  public static final String ENHANCE_DESCRIPTION = "/enhance/description";
  public static final String GENERATE_ADDITIONAL_DESCRIPTION = "/generate/additionalDescription";
  public static final String VISUAL_FEATURES = "visualFeatures";
  public static final String CHECK_ADULT = "Adult";
  public static final String FILTER_ADULT_CONTENT_KEY_HEADER = "Ocp-Apim-Subscription-Key";
  public static final String FILTER_ADULT_CONTENT_KEY = "883d0b6426834e5998a9be11041247a2";
  public static final String TITLE = "{title}";
  public static final String CATEGORY = "{category}";
  public static final String RATE_LIMITING_COUNT_KEY = "count";
  public static final String RATE_LIMITING_TIME_KEY = "time";

  public static class ProductControllerConstants {
    public static final String PRODUCT_BASE_URL = "/api/v1/product";
    public static final String SUCCESS = "SUCCESS";
  }

  public static class PromtGenerationConstants {
    public static final String USER_ROLE = "user";
    public static final String SYSTEM_ROLE = "system";
    public static final String OTHER_PRODUCT_CATEGORY = "One specific";
    public static final int FIXED_TEMPERATURE = 1;
    public static final double FIXED_TOP_P = 0.95;
  }
}