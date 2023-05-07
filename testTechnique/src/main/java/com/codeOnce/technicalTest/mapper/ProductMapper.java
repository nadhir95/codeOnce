package com.codeOnce.technicalTest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.codeOnce.technicalTest.model.dto.ProductDTO;
import com.codeOnce.technicalTest.model.entity.ProductEntity;
@Component
public class ProductMapper {

    private ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    public ProductDTO toDto(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    public List<ProductDTO> toDtoList(List<ProductEntity> productList) {
        return productList.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }
}
