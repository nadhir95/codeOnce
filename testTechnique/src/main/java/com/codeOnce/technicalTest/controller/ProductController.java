package com.codeOnce.technicalTest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
        @ApiResponse(responseCode = ErrorConstant.STATUS_200, description = "product list returned successfully", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductEntity.class))
        }),
        @ApiResponse(responseCode = ErrorConstant.STATUS_400, description = "No product found or invalid input", content = @Content),
        @ApiResponse(responseCode = ErrorConstant.STATUS_500, description = "Internal Server Error", content = @Content),
        @ApiResponse(responseCode = ErrorConstant.STATUS_503, description = "Service Unavailable", content = @Content)
    }) 
    public ResponseEntity<?> getAvailableProductsByCategory(@RequestParam String categoryName) {

        try {
            List<ProductDTO> products = productService.getAvailableProductsByCategory(categoryName);
     
                return ResponseEntity.ok(products);
            
        } catch (InvalidInputException ex) {
        	 ObjectMapper mapper = new ObjectMapper();
             ObjectNode messageJson = mapper.createObjectNode();            
             messageJson.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(messageJson);
        }
    }
        
}