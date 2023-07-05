package com.example.ONDC.sellerApp.ONDCSellerApp.db.entity;

import com.example.ONDC.sellerApp.ONDCSellerApp.db.convertor.ProductCategoryConvertor;
import com.example.ONDC.sellerApp.ONDCSellerApp.db.convertor.ProductMetaInfoConvertor;
import com.example.ONDC.sellerApp.ONDCSellerApp.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "category")
  @Convert(converter = ProductCategoryConvertor.class)
  private ProductCategory productCategory;

  @Column(name = "additional_description")
  private String additionalDescription;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "price")
  private double price;

  @Column(name = "net_quantity")
  private String netQuantity;

  @Column(name = "meta_info")
  @Convert(converter = ProductMetaInfoConvertor.class)
  private ProductMetaInfo metaInfo;

  @CreationTimestamp
  @Column(name = "created_at")
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Timestamp updatedAt;
}
