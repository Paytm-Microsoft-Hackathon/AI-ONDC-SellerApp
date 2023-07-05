package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  public <T> HttpHeaders executePostRequest(final String url, Class<T> clazz, Object requestBody, final Map<String, String> headers, final MultiValueMap<String, String> params)
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
      return responseEntity.getHeaders();
    } catch (Exception e) {
      log.error("SOMETHING WENT WRONG ERROR: ", e);
      throw new ONDCProductException(ProductException.SOMETHING_WENT_WRONG);
    }
  }

  public <T> T executeGetRequest(final String url, Class<T> clazz, final Map<String, String> headers, final MultiValueMap<String, String> params)
    throws ONDCProductException {
    HttpHeaders httpHeaders;
//    ResponseEntity<T> responseEntity;
    httpHeaders = new HttpHeaders();
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      httpHeaders.add(entry.getKey(), entry.getValue());
      httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");
    }
    HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

    try {
      log.info("url: {}, entity: {}, class: {}", url, httpEntity, clazz);
      ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,Object.class);
      log.info("responseEntity: {}",responseEntity);
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.convertValue(responseEntity.getBody(),clazz);
    } catch (Exception e) {
      log.error("SOMETHING WENT WRONG ERROR: ", e);
      throw new ONDCProductException(ProductException.SOMETHING_WENT_WRONG);
    }
  }
}
