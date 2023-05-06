package com.codeOnce.technicalTest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeOnce.technicalTest.model.entity.CategoryEntity;



public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	Optional<CategoryEntity> findByNameIgnoreCase(String categoryName);

}
