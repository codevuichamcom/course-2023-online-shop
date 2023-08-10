package com.funnycode.onlineshop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "tbl_color")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String colorName;
}
