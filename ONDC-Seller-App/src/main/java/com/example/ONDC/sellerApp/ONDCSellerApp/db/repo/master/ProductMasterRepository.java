package com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.master;

import com.example.ONDC.sellerApp.ONDCSellerApp.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMasterRepository extends JpaRepository<Product, Long> {
}
