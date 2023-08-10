package com.funnycode.onlineshop.service.impl;

import com.funnycode.onlineshop.dto.OrderDTOCreate;
import com.funnycode.onlineshop.dto.OrderDTOResponse;
import com.funnycode.onlineshop.dto.OrderDetailDTOCreate;
import com.funnycode.onlineshop.entity.Account;
import com.funnycode.onlineshop.entity.Order;
import com.funnycode.onlineshop.entity.OrderDetail;
import com.funnycode.onlineshop.entity.Shipping;
import com.funnycode.onlineshop.exception.OnlineShopException;
import com.funnycode.onlineshop.mapper.OrderDetailMapper;
import com.funnycode.onlineshop.mapper.OrderMapper;
import com.funnycode.onlineshop.mapper.ShippingMapper;
import com.funnycode.onlineshop.repository.AccountRepository;
import com.funnycode.onlineshop.repository.OrderDetailRepository;
import com.funnycode.onlineshop.repository.OrderRepository;
import com.funnycode.onlineshop.repository.ShippingRepository;
import com.funnycode.onlineshop.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    ShippingRepository shippingRepository;
    OrderDetailRepository orderDetailRepository;
    AccountRepository accountRepository;

    @Override
    @Transactional
    public OrderDTOResponse processOrder(OrderDTOCreate orderDTOCreate) {

        // Save shipping
        Shipping shipping = ShippingMapper.toShipping(orderDTOCreate.getShippingDTOCreate());
        shipping = shippingRepository.save(shipping);

        // Save order
        Order order = OrderMapper.toOrder(orderDTOCreate);
        order.setShipping(shipping);
        List<OrderDetailDTOCreate> orderDetailDTOCreateList = orderDTOCreate.getOrderDetailDTOCreates();

        double totalPrice = calculateTotalPrice(orderDetailDTOCreateList);
        order.setTotalPrice(totalPrice);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> OnlineShopException.notFoundException("Username does not exits"));
        order.setCreatedBy(account);

        order = orderRepository.save(order);

        // Save list order detail
        List<OrderDetail> orderDetailList = saveOrderDetails(order, orderDetailDTOCreateList);
        order.setOrderDetails(orderDetailList);

        return OrderMapper.toOrderDTOResponse(order);
    }

    private double calculateTotalPrice(List<OrderDetailDTOCreate> orderDetailDTOCreateList) {
        return orderDetailDTOCreateList.stream()
                .mapToDouble(orderDetailDTOCreate -> orderDetailDTOCreate.getQuantity()
                        * orderDetailDTOCreate.getProductPrice())
                .sum();
    }

    private List<OrderDetail> saveOrderDetails(Order order, List<OrderDetailDTOCreate> orderDetailDTOCreateList) {
        List<OrderDetail> orderDetailList = orderDetailDTOCreateList.stream()
                .map(orderDetailDTOCreate -> {
                    OrderDetail orderDetail = OrderDetailMapper.toOrderDetail(orderDetailDTOCreate);
                    orderDetail.setOrder(order);
                    return orderDetail;
                })
                .collect(Collectors.toList());

        return orderDetailRepository.saveAll(orderDetailList);
    }
}
