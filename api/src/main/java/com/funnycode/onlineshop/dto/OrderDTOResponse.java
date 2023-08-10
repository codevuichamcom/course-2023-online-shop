package com.funnycode.onlineshop.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTOResponse {
    int id;
    double totalPrice;
    String note;
    Instant createdDate;
    AccountDTOResponse createdBy;
    ShippingDTOResponse shipping;
    List<OrderDetailDTOResponse> orderDetails;
}
