package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@Service
public class RestTemplateService {
  private final RestTemplate restTemplate = new RestTemplate();

  public <T> T executePostRequest(final String url, Class<T> clazz, Object requestBody, final Map<String, String> headers, final MultiValueMap<String, String> params)
    throws ONDCProductException {
    HttpHeaders httpHeaders;
    ResponseEntity<T> responseEntity;
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
      .queryParams(params);
    httpHeaders = new HttpHeaders();
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      httpHeaders.add(entry.getKey(), entry.getValue());
    }
    HttpEntity<Object> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
    try {
      log.info("url: {}, entity: {}, class: {}", builder.toUriString(), httpEntity, clazz);
      responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, clazz);
      return responseEntity.getBody();
    } catch (Exception e) {
      log.error("SOMETHING WENT WRONG ERROR: ", e);
      throw new ONDCProductException(ProductException.SOMETHING_WENT_WRONG);
    }
  }
}
