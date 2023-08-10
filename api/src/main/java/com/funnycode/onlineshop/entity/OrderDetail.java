package com.funnycode.onlineshop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "tbl_order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String productName;
    double productPrice;
    String productImageUrl;
    int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    Order order;
}
