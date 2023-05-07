package com.codeOnce.technicalTest.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeOnce.technicalTest.exception.InvalidInputException;
import com.codeOnce.technicalTest.mapper.ProductMapper;
import com.codeOnce.technicalTest.model.dto.ProductDTO;
import com.codeOnce.technicalTest.model.entity.CategoryEntity;
import com.codeOnce.technicalTest.model.entity.ProductEntity;
import com.codeOnce.technicalTest.repository.CategoryRepository;
import com.codeOnce.technicalTest.repository.ProductRepository;
import com.codeOnce.technicalTest.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<ProductDTO> getAvailableProductsByCategory(String categoryName) throws InvalidInputException {
		try {
			Optional<CategoryEntity> category = categoryRepository.findByNameIgnoreCase(categoryName);

			if (!category.isPresent()) {
				throw new InvalidInputException("La catégorie " + categoryName + " n'existe pas");
			}

			List<ProductEntity> products = productRepository.findByCategoryNameIgnoreCaseAndStockGreaterThan(categoryName, 0);
			if (products.isEmpty()) {
				throw new InvalidInputException(
						"Il n'y a pas de produits disponibles dans la catégorie " + categoryName);
			}

			return productMapper.toDtoList(products);
		} catch (InvalidInputException ex) {
			throw new InvalidInputException("Invalid input: " + ex.getMessage());

		}

	}
}
