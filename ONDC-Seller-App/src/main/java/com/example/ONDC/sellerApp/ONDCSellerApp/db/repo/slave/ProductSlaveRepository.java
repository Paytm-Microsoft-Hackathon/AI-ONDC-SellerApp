package com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.slave;

import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSlaveRepository extends JpaRepository<Product, Long>{
  List<Product> findAll();
}
