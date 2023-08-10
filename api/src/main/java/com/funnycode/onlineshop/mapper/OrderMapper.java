package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.dto.OrderDTOCreate;
import com.funnycode.onlineshop.dto.OrderDTOResponse;
import com.funnycode.onlineshop.dto.OrderDetailDTOResponse;
import com.funnycode.onlineshop.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTOResponse toOrderDTOResponse(Order order) {
        List<OrderDetailDTOResponse> orderDetailDTOResponseList = order.getOrderDetails().stream()
                .map(OrderDetailMapper::toOrderDetailDTOResponse)
                .collect(Collectors.toList());

        return OrderDTOResponse.builder()
                .id(order.getId())
                .note(order.getNote())
                .totalPrice(order.getTotalPrice())
                .orderDetails(orderDetailDTOResponseList)
                .shipping(ShippingMapper.toShippingDTOResponse(order.getShipping()))
                .createdDate(order.getCreatedDate())
                .createdBy(AccountMapper.toAccountDTOResponse(order.getCreatedBy()))
                .build();
    }

    public static Order toOrder(OrderDTOCreate orderDTOCreate) {
        return Order.builder()
                .note(orderDTOCreate.getNote())
                .build();
    }
}
