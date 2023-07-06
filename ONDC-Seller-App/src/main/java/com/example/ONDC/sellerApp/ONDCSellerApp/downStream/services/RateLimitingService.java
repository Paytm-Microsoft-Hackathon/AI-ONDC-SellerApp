package com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateLimitingService {

  private final RedisTemplate<String, String> redisTemplate;
  private RedisServer redisServer;
  private static final DateTimeFormatter dateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

  @PostConstruct
  public void setUp() {
    try {
//      redisServer = RedisServer.builder().port(6370).setting("maxmemory 128M").build();
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

  public boolean limitRate(){
   String count = redisTemplate.opsForValue().get("generateDescription");
   String initTime = redisTemplate.opsForValue().get("generateDescriptionTime");
   if(StringUtils.isEmpty(initTime)){
     return false;
   }
    log.info("Before count :{}",count);
    LocalDateTime localDateTime= LocalDateTime.parse(initTime, dateTimeFormatter);
    if(localDateTime.isBefore(LocalDateTime.now().minus(60, ChronoUnit.SECONDS))) {
      this.redisTemplate.opsForValue()
        .set("generateDescriptionDateTime", LocalDateTime.now().toString(), 120, TimeUnit.SECONDS);
      this.redisTemplate.opsForValue()
        .set("generateDescription", "0", 120, TimeUnit.SECONDS);
      return false;
    }
    if(StringUtils.isEmpty(count))
      return false;
    Long countValue = Long.valueOf(count);
   if(countValue>5)
     return true;
   return false;
  }

  public void increaseCount(String key){
    String count = redisTemplate.opsForValue().get(key);
    if(StringUtils.isEmpty(count))
      count="0";
    count =String.valueOf(Long.parseLong(count)+1L);
    log.info("After count :{}",count);
    this.redisTemplate.opsForValue()
      .set("generateDescription", count, 60, TimeUnit.SECONDS);
  }

}
