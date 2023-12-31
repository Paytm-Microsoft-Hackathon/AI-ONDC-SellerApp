package com.example.ONDC.sellerApp.AIProductCataloging.service;

import com.example.ONDC.sellerApp.AIProductCataloging.db.entity.Product;
import com.example.ONDC.sellerApp.AIProductCataloging.db.entity.ProductMetaInfo;
import com.example.ONDC.sellerApp.AIProductCataloging.db.repo.master.ProductMasterRepository;
import com.example.ONDC.sellerApp.AIProductCataloging.db.repo.slave.ProductSlaveRepository;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.AIService;
import com.example.ONDC.sellerApp.AIProductCataloging.downStream.services.Models.FilterAdultContentResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductCategory;
import com.example.ONDC.sellerApp.AIProductCataloging.exceptions.ONDCProductException;
import com.example.ONDC.sellerApp.AIProductCataloging.model.AddProductRequest;
import com.example.ONDC.sellerApp.AIProductCataloging.model.FetchProductResponse;
import com.example.ONDC.sellerApp.AIProductCataloging.util.FileUtil;
import com.example.ONDC.sellerApp.AIProductCataloging.util.OffsetBasedPageRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductException.IMAGE_CONTAINS_ADULT_CONTENT;
import static com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductException.MANDATORY_PARAMETERS_MISSING_IN_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
   private final ProductMasterRepository productMasterRepository;
   private final AIService aiService;
   private final ProductSlaveRepository productSlaveRepository;

  public void addProduct(AddProductRequest request,
                           List<MultipartFile> images) throws ONDCProductException, IOException {

    validateAddProductRequest(request);
    List<String> imageFilePath = saveAndGenerateLocalFilePath(images, request.getImageUrl(), request.getTitle());
    checkForAdultContent(imageFilePath);
    saveProductInDb(request, imageFilePath);
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

  private void saveProductInDb(AddProductRequest request,
                               List<String> filePaths) {
    Product product =
        Product
            .builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .productCategory(ProductCategory.fromValue(request.getCategory()))
            .additionalDescription(request.getAdditionalInfo())
            .createdBy(request.getCreatedBy())
            .price(request.getPrice())
            .netQuantity(request.getNetQuantity())
            .metaInfo(ProductMetaInfo.builder().imagesPath(filePaths).build())
            .build();
    product = productMasterRepository.save(product);
    log.info("[saveProductInDb] Product created successfully with id: {} | title: {}", product.getId(), request.getTitle());
  }

  public List<FetchProductResponse> getProducts(int offset, int limit) {
    Page<Product> productInPages = productSlaveRepository.findAll(new OffsetBasedPageRequest(
        offset,
        limit,
        Sort.by(new Sort.Order(Sort.Direction.DESC, "id"))));
    List<Product> products = productInPages.getContent();
    List<FetchProductResponse> response = new ArrayList<>();
    products.forEach(product -> response.add(FetchProductResponse
        .builder()
        .title(product.getTitle())
        .description(product.getDescription())
        .productCategory(product.getProductCategory().getValue())
        .createdBy(product.getCreatedBy())
        .price(product.getPrice())
        .netQuantity(product.getNetQuantity())
        .imageUrls(Objects.nonNull(product.getMetaInfo()) ? product.getMetaInfo().getImagesPath() : new ArrayList<>())
        .imageStream(getImage(product.getMetaInfo()))
        .build()));
    return response;
  }

  private List<byte[]> getImage(ProductMetaInfo metaInfo) {

    List<byte[]> images = new ArrayList<>();
    if (Objects.isNull(metaInfo) || CollectionUtils.isEmpty(metaInfo.getImagesPath())) {
      return images;
    }

    metaInfo.getImagesPath().forEach(imagePath -> {
      try (FileInputStream fileInputStream = new FileInputStream(imagePath)) {
      images.add(StreamUtils.copyToByteArray(fileInputStream));
      } catch (Exception ex) {
        log.error("[getImage] Error while parsin image | path: {} | error: ", imagePath, ex);
      }
    });

    return images;
  }
}
