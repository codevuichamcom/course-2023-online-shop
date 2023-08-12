package com.funnycode.onlineshop.service;

import com.funnycode.onlineshop.dto.PagingDTOResponse;
import com.funnycode.onlineshop.dto.ProductDTOCreate;
import com.funnycode.onlineshop.dto.ProductDTOFilter;
import com.funnycode.onlineshop.dto.ProductDTOResponse;

import java.util.List;

public interface ProductService {
    List<ProductDTOResponse> getProductTrending();

    List<ProductDTOResponse> getProductsBestSeller();

    PagingDTOResponse searchProduct(ProductDTOFilter productDTOFilter);

    ProductDTOResponse getProductById(int id);

    ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate);
}
