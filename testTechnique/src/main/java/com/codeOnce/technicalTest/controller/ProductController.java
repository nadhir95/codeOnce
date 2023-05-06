package com.codeOnce.technicalTest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeOnce.technicalTest.constant.ErrorConstant;
import com.codeOnce.technicalTest.constant.TestConstant;
import com.codeOnce.technicalTest.exception.InvalidInputException;
import com.codeOnce.technicalTest.model.dto.ProductDTO;
import com.codeOnce.technicalTest.model.entity.ProductEntity;
import com.codeOnce.technicalTest.service.ProductService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(TestConstant.ACCESS + TestConstant.PRODUCT)
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = ErrorConstant.STATUS_200, description = " Last request history line returned successfully", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductEntity.class)) }),
			@ApiResponse(responseCode = ErrorConstant.STATUS_400, description = "Invalid Request added", content = @Content),
			@ApiResponse(responseCode = ErrorConstant.STATUS_500, description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = ErrorConstant.STATUS_503, description = "Service Unavailable", content = @Content) })
    public List<ProductDTO> getAvailableProductsByCategory(@RequestParam String categoryName) throws InvalidInputException {
        return productService.getAvailableProductsByCategory(categoryName);
    }
        
}