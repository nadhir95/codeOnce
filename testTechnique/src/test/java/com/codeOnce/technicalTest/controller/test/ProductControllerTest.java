package com.codeOnce.technicalTest.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.codeOnce.technicalTest.constant.TestConstant;
import com.codeOnce.technicalTest.controller.ProductController;
import com.codeOnce.technicalTest.mapper.ProductMapper;
import com.codeOnce.technicalTest.service.ProductService;
import com.codeOnce.technicalTest.model.dto.ProductDTO;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)

public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;

	@MockBean
	ProductMapper productMapper;
	@DisplayName("GET all request status ")
	@Test
	void getAllRequestStatus() throws Exception {
      String categoryName = "testCategory";
      List<ProductDTO> expectedProducts = Arrays.asList(new ProductDTO(), new ProductDTO());
      when(productService.getAvailableProductsByCategory(categoryName)).thenReturn(expectedProducts);
		
		
		
		mockMvc.perform(
				get("/" + TestConstant.ACCESS + TestConstant.PRODUCT).param("categoryName", categoryName)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}

