package com.example.ONDC.sellerApp.ONDCSellerApp.service;

import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.Product;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.ProductMetaInfo;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.Product;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.master.ProductMasterRepository;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.slave.ProductSlaveRepository;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.AIService;
import com.example.ONDC.sellerApp.ONDCSellerApp.downStream.services.Models.FilterAdultContentResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import com.example.ONDC.sellerApp.ONDCSellerApp.exceptions.ONDCProductException;
import com.example.ONDC.sellerApp.ONDCSellerApp.model.AddProductRequest;
import com.example.ONDC.sellerApp.ONDCSellerApp.model.FetchProductResponse;
import com.example.ONDC.sellerApp.ONDCSellerApp.util.FileUtil;
import com.example.ONDC.sellerApp.ONDCSellerApp.util.OffsetBasedPageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException.IMAGE_CONTAINS_ADULT_CONTENT;
import static com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductException.MANDATORY_PARAMETERS_MISSING_IN_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
  @Autowired private ProductMasterRepository productMasterRepository;
  @Autowired private AIService aiService;
  @Autowired ProductSlaveRepository productSlaveRepository;

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

  public void addProductV2(AddProductRequest request,
                           List<MultipartFile> images) throws ONDCProductException, IOException {

    validateAddProductRequest(request);
    List<String> imageFilePath = saveAndGenerateLocalFilePath(images, request.getImageUrl(), request.getTitle());
    checkForAdultContent(imageFilePath);
    saveProductInDb(request.getTitle(), request.getDescription(), request.getCategory(), request.getAdditionalInfo(),
        request.getCreatedBy(), request.getPrice(), request.getNetQuantity(), imageFilePath);
  }

  private void validateAddProductRequest(AddProductRequest request) throws ONDCProductException {
    if (StringUtils.isEmpty(request.getTitle())
        || StringUtils.isEmpty(request.getCategory())
        || StringUtils.isEmpty(request.getCreatedBy())
        || StringUtils.isEmpty(request.getPrice())
        || StringUtils.isEmpty(request.getDescription())) {
      log.warn("[validateAddProductRequest] Mandatory parameter missing in request | request: {}", request);
      throw new ONDCProductException(MANDATORY_PARAMETERS_MISSING_IN_REQUEST);
    }
  }

  private List<String> saveAndGenerateLocalFilePath(List<MultipartFile> images,
                                       List<String> imageUrl,
                                       String title) throws IOException, ONDCProductException {
    List<String> filePath = new ArrayList<>();

    // Save seller uploaded image
    if (!CollectionUtils.isEmpty(images)) {
      for (var image : images) {
        filePath.add(FileUtil.saveFile(image.getInputStream(), title));
      }
    }

    // Save AI generate image
    if (!CollectionUtils.isEmpty(imageUrl)) {
      for (var image : imageUrl) {
        filePath.add(FileUtil.saveFileLocallyFromURL(image, title));
      }
    }

    return filePath;
  }

  private void checkForAdultContent(List<String> imageFilePath) throws ONDCProductException {
    int counter = 0;
    for (var image : imageFilePath) {
      FilterAdultContentResponse response = aiService.filterAdultContent(image);
      if (Objects.nonNull(response)
          && Objects.nonNull(response.getAdult())
          && (Boolean.TRUE.equals(response.getAdult().getIsAdultContent())
          || Boolean.TRUE.equals(response.getAdult().getIsGoryContent())
          || Boolean.TRUE.equals(response.getAdult().getIsRacyContent()))) {
        counter++;
      }
    }

    if (counter > 0) {
      log.warn("[checkForAdultContent] {} image contains inappropriate content", counter);
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

  public List<FetchProductResponse> getProducts(int offset, int limit) {
    Page<Product> productInPages = productSlaveRepository.findAll(new OffsetBasedPageRequest(
        offset,
        limit,
        Sort.by(new Sort.Order(Sort.Direction.DESC, "id"))));
    List<Product> products = productInPages.getContent();
    List<FetchProductResponse> response = new ArrayList<>();
    products.forEach(product -> {
      response.add(FetchProductResponse
          .builder()
          .title(product.getTitle())
          .description(product.getDescription())
          .productCategory(product.getProductCategory().getValue())
          .createdBy(product.getCreatedBy())
          .price(product.getPrice())
          .netQuantity(product.getNetQuantity())
          .imageUrls(Objects.nonNull(product.getMetaInfo()) ? product.getMetaInfo().getImagesPath() : new ArrayList<>())
          .build());
    });
    return response;
  }
}
