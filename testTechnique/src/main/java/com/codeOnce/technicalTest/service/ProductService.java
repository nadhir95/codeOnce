package com.codeOnce.technicalTest.service;
import java.util.List;

import com.codeOnce.technicalTest.exception.InvalidInputException;
import com.codeOnce.technicalTest.model.dto.ProductDTO;

public interface ProductService {
    public List<ProductDTO> getAvailableProductsByCategory(String categoryName) throws InvalidInputException;

}
