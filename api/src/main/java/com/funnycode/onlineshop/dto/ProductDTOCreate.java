package com.funnycode.onlineshop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOCreate {
    String name;
    double price;
    int quantity;
    String specification;
    String availability;
    String imageUrl;
    String summary;
    String description;
    int categoryId;
    int brandId;
    int colorId;
}
