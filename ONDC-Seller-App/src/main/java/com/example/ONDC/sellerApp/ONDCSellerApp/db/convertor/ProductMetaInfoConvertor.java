package com.example.ONDC.sellerApp.ONDCSellerApp.db.convertor;

import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.ProductMetaInfo;
import com.example.ONDC.sellerApp.ONDCSellerApp.util.JsonUtil;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductMetaInfoConvertor implements AttributeConverter<ProductMetaInfo, String> {
  @Override
  public ProductMetaInfo convertToEntityAttribute(String metaInfo) {
    return JsonUtil.parseJson(metaInfo, ProductMetaInfo.class);
  }

  @Override
  public String convertToDatabaseColumn(ProductMetaInfo productMetaInfo) {
    return JsonUtil.serialiseJson(productMetaInfo);
  }
}
