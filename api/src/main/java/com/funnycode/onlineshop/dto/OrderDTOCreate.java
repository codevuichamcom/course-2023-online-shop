package com.funnycode.onlineshop.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTOCreate {
    String note;
    ShippingDTOCreate shippingDTOCreate;
    List<OrderDetailDTOCreate> orderDetailDTOCreates;
}
