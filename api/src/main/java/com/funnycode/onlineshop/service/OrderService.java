package com.funnycode.onlineshop.service;

import com.funnycode.onlineshop.dto.OrderDTOCreate;
import com.funnycode.onlineshop.dto.OrderDTOResponse;

public interface OrderService {
    OrderDTOResponse processOrder(OrderDTOCreate orderDTOCreate);
}
