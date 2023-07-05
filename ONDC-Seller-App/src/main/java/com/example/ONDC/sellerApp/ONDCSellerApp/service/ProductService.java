package com.example.ONDC.sellerApp.ONDCSellerApp.service;

import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.Product;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.ProductMetaInfo;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.master.ProductMasterRepository;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.AIService;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.FilterAdultContentResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import com.example.ONDC.sellerApp.ONDCSellerApp.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException.IMAGE_CONTAINS_ADULT_CONTENT;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
  @Autowired private ProductMasterRepository productMasterRepository;
  @Autowired private AIService aiService;

  public void addProduct(String title,
                         String description,
                         Integer category,
                         String additionalInfo,
                         String createdBy,
                         double price,
                         String netQuantity,
                         List<MultipartFile> images,
                         List<String> imageUrl) throws ONDCProductException, IOException {

    List<String> imageFilePath = saveAndGenerateLocalFilePath(images, imageUrl, title);
    checkForAdultContent(imageFilePath);
    saveProductInDb(title, description, category, additionalInfo, createdBy, price, netQuantity, imageFilePath);
  }

  private List<String> saveAndGenerateLocalFilePath(List<MultipartFile> images,
                                       List<String> imageUrl,
                                       String title) throws IOException, ONDCProductException {
    List<String> filePath = new ArrayList<>();

    // Save seller uploaded image
    for (var image : images) {
      filePath.add(FileUtil.saveFile(image.getInputStream(), title));
    }

    // Save AI generate image
    for (var image : imageUrl) {
      filePath.add(FileUtil.saveFileLocallyFromURL(image, title));
    }

    return filePath;
  }

  private void checkForAdultContent(List<String> imageFilePath) throws ONDCProductException {
    int counter = 0;
    for (var image : imageFilePath) {
      FilterAdultContentResponse response = aiService.filterAdultContent(image);
      if (Objects.nonNull(response)
          && Objects.nonNull(response.getAdultContentDetails())
          && Boolean.TRUE.equals(response.getAdultContentDetails().getIsAdultContent())) {
        counter++;
      }
    }

    if (counter > 0) {
      log.warn("[checkForAdultContent] {} image containes adult content", counter);
      throw new ONDCProductException(String.format(IMAGE_CONTAINS_ADULT_CONTENT.getMessage(), counter),
          IMAGE_CONTAINS_ADULT_CONTENT.getCode(), HttpStatus.BAD_REQUEST);
    }
  }

  private void saveProductInDb(String title,
                               String description,
                               Integer category,
                               String additionalInfo,
                               String createdBy,
                               double price,
                               String netQuantity,
                               List<String> filePaths) {
    Product product =
        Product
            .builder()
            .title(title)
            .description(description)
            .productCategory(ProductCategory.fromValue(category))
            .additionalDescription(additionalInfo)
            .createdBy(createdBy)
            .price(price)
            .netQuantity(netQuantity)
            .metaInfo(ProductMetaInfo.builder().imagesPath(filePaths).build())
            .build();
    product = productMasterRepository.save(product);
    log.info("[saveProductInDb] Product created successfully with id: {} | title: {}", product.getId(), title);
  }
}
