package com.funnycode.onlineshop.dto;


import com.funnycode.onlineshop.util.Constant;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOFilter {
    Integer pageIndex = 1;
    Integer pageSize = Constant.DEFAULT_PAGE_SIZE;
    Integer categoryId;
    Integer brandId;
    Integer colorId;
    Double priceFrom = 0d;
    Double priceTo = 100d;
    String sortByPrice;
    String name = "";
}
