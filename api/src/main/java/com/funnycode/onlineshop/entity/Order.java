package com.funnycode.onlineshop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    double totalPrice;
    String note;

    @CreatedDate
    Instant createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    Account createdBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    Shipping shipping;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails = new ArrayList<>();
}
