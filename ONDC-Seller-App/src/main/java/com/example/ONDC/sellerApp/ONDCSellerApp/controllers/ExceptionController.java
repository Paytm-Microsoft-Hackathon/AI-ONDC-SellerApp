package com.example.ONDC.sellerApp.ONDCSellerApp.controllers;

import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.Error;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(ONDCProductException.class)
  public ResponseEntity<Error> exceptionHandler(
      ONDCProductException ondcProductException, HttpServletRequest request) {
    return new ResponseEntity<>(new Error(new Error.ErrorMessage(ondcProductException.getMessage())), ondcProductException.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> exceptionHandler(Exception ex, HttpServletRequest request) {
    log.error("Exception occurred in for Exception class for {} :", request.getRequestURL(), ex);
    var error = new Error();
    var errorMessage = new Error.ErrorMessage();
    errorMessage.setMessage("Something went wrong!");
    error.setErrorMessage(errorMessage);
    return new ResponseEntity<>(
        error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
