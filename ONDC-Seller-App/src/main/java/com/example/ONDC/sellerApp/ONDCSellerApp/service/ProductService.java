package com.example.ONDC.sellerApp.ONDCSellerApp.service;

import com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.master.ProductMasterRepository;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.slave.ProductSlaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
  @Autowired ProductMasterRepository productMasterRepository;
  @Autowired ProductSlaveRepository productSlaveRepository;

  public void addProduct(String title,
                         String description,
                         Integer category,
                         String additionalInfo,
                         Long createdBy,
                         double price,
                         String netQuantity,
                         List<MultipartFile> images,
                         List<String> imageUrl) {

    convertImageUrlToIn
    // convert image url to inputstream
    if (!CollectionUtils.isEmpty(imageUrl)) {

    }

  }
}
