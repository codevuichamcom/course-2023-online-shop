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
public class OrderDetailDTOResponse {
    int id;
    String productName;
    double productPrice;
    String productImageUrl;
    int quantity;
}
