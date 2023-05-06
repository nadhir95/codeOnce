package com.codeOnce.technicalTest.service.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codeOnce.technicalTest.exception.InvalidInputException;
import com.codeOnce.technicalTest.impl.ProductServiceImpl;
import com.codeOnce.technicalTest.mapper.ProductMapper;
import com.codeOnce.technicalTest.model.dto.CategoryDTO;
import com.codeOnce.technicalTest.model.dto.ProductDTO;
import com.codeOnce.technicalTest.model.entity.CategoryEntity;
import com.codeOnce.technicalTest.model.entity.ProductEntity;
import com.codeOnce.technicalTest.repository.CategoryRepository;
import com.codeOnce.technicalTest.repository.ProductRepository;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;
	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private ProductMapper ProductMapper;
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Test
	
	void ShouldReturnProductList() throws InvalidInputException {
		String categoryName = "Alimentation";
		CategoryEntity foodCategoryEntity = new CategoryEntity(1L,categoryName);
		Optional<CategoryEntity> foodCategoryEntityOptional = Optional.ofNullable(new CategoryEntity(1L,categoryName));

		ProductEntity milkEntity  = new ProductEntity(1L, "Lait",23,foodCategoryEntity);
		List<ProductEntity> listEntities = new ArrayList<>();
		listEntities.add(milkEntity);
		
		
		CategoryDTO foodCategory = new CategoryDTO(1L,categoryName);
		ProductDTO milk  = new ProductDTO(1L, "Lait",23,foodCategory);
		List<ProductDTO> list = new ArrayList<>();
		list.add(milk);
		
		Mockito.when(categoryRepository.findByNameIgnoreCase(categoryName)).thenReturn(foodCategoryEntityOptional);
		Mockito.when(productRepository.findByCategoryNameAndStockGreaterThan(categoryName,0)).thenReturn(listEntities);
		Mockito.when(ProductMapper.toDtoList(listEntities)).thenReturn(list);
		List<ProductDTO> productList = productService.getAvailableProductsByCategory("Alimentation");

		assertEquals(1, productList.size());
	}
	


    @Test
    void testGetAvailableProductsByCategory_WhenCategoryDoesNotExist() {

        String categoryName = "NonExistingCategory";

        Mockito.when(categoryRepository.findByNameIgnoreCase(categoryName)).thenReturn(Optional.empty());


        Assertions.assertThrows(InvalidInputException.class, () -> {
            productService.getAvailableProductsByCategory(categoryName);
        }, "La catégorie " + categoryName + " n'existe pas");
    }

    @Test
    void testGetAvailableProductsByCategory_WhenNoProductsAreAvailable() {
        
        String categoryName = "Books";
        CategoryEntity category = new CategoryEntity(1L, categoryName);

        Mockito.when(categoryRepository.findByNameIgnoreCase(categoryName)).thenReturn(Optional.of(category));
        Mockito.when(productRepository.findByCategoryNameAndStockGreaterThan(categoryName, 0)).thenReturn(Arrays.asList());


        Assertions.assertThrows(InvalidInputException.class, () -> {
            productService.getAvailableProductsByCategory(categoryName);
        }, "Il n'y a pas de produits disponibles dans la catégorie " + categoryName);
    }

}



