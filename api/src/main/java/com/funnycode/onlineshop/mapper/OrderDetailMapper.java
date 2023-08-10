package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.dto.OrderDetailDTOCreate;
import com.funnycode.onlineshop.dto.OrderDetailDTOResponse;
import com.funnycode.onlineshop.entity.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetailDTOResponse toOrderDetailDTOResponse(OrderDetail orderDetail) {
        return OrderDetailDTOResponse.builder()
                .id(orderDetail.getId())
                .productName(orderDetail.getProductName())
                .productPrice(orderDetail.getProductPrice())
                .productImageUrl(orderDetail.getProductImageUrl())
                .quantity(orderDetail.getQuantity())
                .build();
    }

    public static OrderDetail toOrderDetail(OrderDetailDTOCreate orderDetailDTOCreate) {
        return OrderDetail.builder()
                .productName(orderDetailDTOCreate.getProductName())
                .productPrice(orderDetailDTOCreate.getProductPrice())
                .productImageUrl(orderDetailDTOCreate.getProductImageUrl())
                .quantity(orderDetailDTOCreate.getQuantity())
                .build();

    }
}