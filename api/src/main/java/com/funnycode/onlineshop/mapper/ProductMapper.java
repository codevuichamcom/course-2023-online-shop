package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.dto.ProductDTOResponse;
import com.funnycode.onlineshop.entity.Product;

public class ProductMapper {
    public static ProductDTOResponse toProductDTOResponse(Product product) {
        return ProductDTOResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .specification(product.getSpecification())
                .availability(product.getAvailability())
                .imageUrl(product.getImageUrl())
                .summary(product.getSummary())
                .description(product.getDescription())
                .category(CategoryMapper.toCategoryDTOResponse(product.getCategory()))
                .build();
    }

}
