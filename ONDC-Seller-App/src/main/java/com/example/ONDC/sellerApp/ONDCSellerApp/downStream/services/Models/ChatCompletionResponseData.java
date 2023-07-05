package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponseData {

  private String role;
  private String content;
}
