package com.codeOnce.technicalTest.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeOnce.technicalTest.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategoryNameAndStockGreaterThan(String categoryName, int stock);
    
}
