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

    public ProductDTO toDto(ProductEntity entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    public List<ProductDTO> toDtoList(List<ProductEntity> entityList) {
        return entityList.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }
}
