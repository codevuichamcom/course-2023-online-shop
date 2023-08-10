package com.funnycode.onlineshop.controller;

import com.funnycode.onlineshop.dto.OrderDTOCreate;
import com.funnycode.onlineshop.dto.OrderDTOResponse;
import com.funnycode.onlineshop.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static com.funnycode.onlineshop.util.Constant.API_VERSION;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(API_VERSION + "/orders")
@CrossOrigin
public class OrderController {

    OrderService orderService;

    @PostMapping
    public OrderDTOResponse processOrder(@RequestBody OrderDTOCreate orderDTOCreate) {
        return orderService.processOrder(orderDTOCreate);
    }
}
