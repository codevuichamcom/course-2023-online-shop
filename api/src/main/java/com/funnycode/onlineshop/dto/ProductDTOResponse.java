package com.funnycode.onlineshop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOResponse {
    int id;
    String name;
    double price;
    int quantity;
    String specification;
    String availability;
    String imageUrl;
    String summary;
    String description;
    CategoryDTOResponse category;
}
