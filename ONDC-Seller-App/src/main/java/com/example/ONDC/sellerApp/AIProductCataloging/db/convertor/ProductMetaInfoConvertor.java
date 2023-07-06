package com.example.ONDC.sellerApp.AIProductCataloging.db.convertor;

import com.example.ONDC.sellerApp.AIProductCataloging.db.entity.ProductMetaInfo;
import com.example.ONDC.sellerApp.AIProductCataloging.util.JsonUtil;
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
