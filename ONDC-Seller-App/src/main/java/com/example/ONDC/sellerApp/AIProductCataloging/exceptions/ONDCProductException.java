package com.example.ONDC.sellerApp.AIProductCataloging.exceptions;

import com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductException;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ONDCProductException extends Exception {
  private String message;
  private String code;
  private HttpStatus status;

  public ONDCProductException(String message, String code, HttpStatus status) {
    this.message = message;
    this.code = code;
    this.status = status;
  }

  public ONDCProductException(ProductException productException) {
    super(productException.getMessage());
    this.status = productException.getStatus();
    this.code = productException.getCode();
    this.message = productException.getMessage();
  }

  public ONDCProductException(final String message) {
    super(message);
    this.message = message;
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
  }

}
