package com.example.ONDC.sellerApp.AIProductCataloging.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ProductException {
  SOMETHING_WENT_WRONG("Something went wrong, Please try again", "ONDC_500", HttpStatus.INTERNAL_SERVER_ERROR),
  IMAGE_DOWNLOAD_FAILED("Image download failed, Please try again later", "ONDC_501", HttpStatus.INTERNAL_SERVER_ERROR),
  IMAGE_CONTAINS_ADULT_CONTENT("%s images provided contains inappropriate and restricted content, Please try again with different images", "ONDC_400", HttpStatus.BAD_REQUEST),
  MANDATORY_PARAMETERS_MISSING_IN_REQUEST("Mandatory parameters are missing in the request, Please try again with different images", "ONDC_401", HttpStatus.BAD_REQUEST);


  private final String message;
  private final String code;
  private final HttpStatus status;

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
