package com.funnycode.onlineshop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    public static final String NOT_IN_STOCK = "NOT_IN_STOCK";
    public static final String IN_STOCK = "IN_STOCK";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    double price;
    int quantity;
    String specification;
    String availability;
    String imageUrl;

    @Column(columnDefinition = "TEXT")
    String summary;

    @Column(columnDefinition = "TEXT")
    String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    Color color;
}
