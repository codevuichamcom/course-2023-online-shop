package com.funnycode.onlineshop.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailDTOCreate {
    String productName;
    double productPrice;
    String productImageUrl;
    int quantity;
}
