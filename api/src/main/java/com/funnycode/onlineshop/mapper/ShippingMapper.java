package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.dto.ShippingDTOCreate;
import com.funnycode.onlineshop.dto.ShippingDTOResponse;
import com.funnycode.onlineshop.entity.Shipping;

public class ShippingMapper {
    public static Shipping toShipping(ShippingDTOCreate shippingDTOCreate) {
        return Shipping.builder()
                .name(shippingDTOCreate.getName())
                .phone(shippingDTOCreate.getPhone())
                .address(shippingDTOCreate.getAddress())
                .build();
    }

    public static ShippingDTOResponse toShippingDTOResponse(Shipping shipping) {
        return ShippingDTOResponse.builder()
                .id(shipping.getId())
                .name(shipping.getName())
                .phone(shipping.getPhone())
                .address(shipping.getAddress())
                .build();
    }
}
