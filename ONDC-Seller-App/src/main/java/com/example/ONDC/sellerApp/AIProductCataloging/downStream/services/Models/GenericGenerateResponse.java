package com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericGenerateResponse<T> {
  private T data;
}
