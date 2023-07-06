package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import com.example.ONDC.sellerApp.ONDCSellerApp.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.RATE_LIMITING_COUNT_KEY;
import static com.example.ONDC.sellerApp.ONDCSellerApp.Constants.RATE_LIMITING_TIME_KEY;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateLimitingService {

  private final RedisTemplate<String, String> redisTemplate;
  private RedisServer redisServer;


  @PostConstruct
  public void setUp() {
    try {
      this.redisServer = new RedisServer(6370);

      redisServer.start();
      this.redisTemplate.opsForValue()
        .set("generateDescription", "0", 60, TimeUnit.SECONDS);
      this.redisTemplate.opsForValue()
        .set("generateDescriptionDateTime", LocalDateTime.now().toString(), 60, TimeUnit.SECONDS);
    } catch (Exception e) {
      //do nothing
    }
  }

  @PreDestroy
  public void tearDown() {
    redisServer.stop();
  }

  public boolean checkForRateLimit(String apiPath) {
    String count = redisTemplate.opsForValue().get(apiPath + "_" + RATE_LIMITING_COUNT_KEY);
    String initTime = redisTemplate.opsForValue().get(apiPath + "_" + RATE_LIMITING_TIME_KEY);
    if (StringUtils.isEmpty(initTime)
        || StringUtils.isEmpty(count)
        || LocalDateTime.parse(initTime, DateUtil.DATE_TIME_FORMATTER).isBefore(LocalDateTime.now().minus(60, ChronoUnit.SECONDS))) {
     // Request is post 1 minute , should be allowed
     return false;
    }

    log.info("Before count :{} | apipath: {}", count, apiPath);
    long countValue = Long.parseLong(count);
    if (countValue >= 2) {
      // Should not be allowed
      return true;
    }

   return false;
  }

  public void increaseCount(String apiPath) {
    String count = redisTemplate.opsForValue().get(apiPath + "_" + RATE_LIMITING_COUNT_KEY);
    String initTime = redisTemplate.opsForValue().get(apiPath + "_" + RATE_LIMITING_TIME_KEY);
    if (StringUtils.isEmpty(initTime)
        || LocalDateTime.parse(initTime, DateUtil.DATE_TIME_FORMATTER)
        .isBefore(LocalDateTime.now().minus(60, ChronoUnit.SECONDS))) {
      this.redisTemplate.opsForValue()
          .set(apiPath + "_" + RATE_LIMITING_TIME_KEY, DateUtil.formatDate(LocalDateTime.now()), 60, TimeUnit.SECONDS);
      this.redisTemplate.opsForValue()
          .set(apiPath + "_" + RATE_LIMITING_COUNT_KEY, "1", 60, TimeUnit.SECONDS);
      log.info("After count :{} | apiPath: {}", count, apiPath);
      // Resetting count as 1 min has been crossed
      return;
    }

    if (StringUtils.isEmpty(count)) {
      count = "0";
    }
    count = String.valueOf(Long.parseLong(count) + 1L);
    log.info("After count :{} | apiPath: {}", count, apiPath);
    this.redisTemplate.opsForValue()
      .set(apiPath + "_" + RATE_LIMITING_COUNT_KEY, count, 60, TimeUnit.SECONDS);
  }
}
