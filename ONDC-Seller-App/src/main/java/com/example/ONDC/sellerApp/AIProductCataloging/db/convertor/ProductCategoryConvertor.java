package com.example.ONDC.sellerApp.AIProductCataloging.db.convertor;

import com.example.ONDC.sellerApp.AIProductCataloging.enums.ProductCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductCategoryConvertor implements AttributeConverter<ProductCategory, Integer>  {
  @Override
  public Integer convertToDatabaseColumn(ProductCategory attribute) {
    if (attribute == null) {
      return null;
    }
    return attribute.getValue();
  }

  @Override
  public ProductCategory convertToEntityAttribute(Integer dbData) {
    if (dbData == null) {
      return null;
    }
    return ProductCategory.fromValue(dbData);
  }
}
