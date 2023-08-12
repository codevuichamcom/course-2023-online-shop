package com.funnycode.onlineshop.service.impl;

import com.funnycode.onlineshop.dto.PagingDTOResponse;
import com.funnycode.onlineshop.dto.ProductDTOCreate;
import com.funnycode.onlineshop.dto.ProductDTOFilter;
import com.funnycode.onlineshop.dto.ProductDTOResponse;
import com.funnycode.onlineshop.entity.Brand;
import com.funnycode.onlineshop.entity.Category;
import com.funnycode.onlineshop.entity.Color;
import com.funnycode.onlineshop.entity.Product;
import com.funnycode.onlineshop.exception.OnlineShopException;
import com.funnycode.onlineshop.mapper.CategoryMapper;
import com.funnycode.onlineshop.mapper.ProductMapper;
import com.funnycode.onlineshop.repository.BrandRepository;
import com.funnycode.onlineshop.repository.CategoryRepository;
import com.funnycode.onlineshop.repository.ColorRepository;
import com.funnycode.onlineshop.repository.ProductCategory;
import com.funnycode.onlineshop.repository.criteria.ProductCategoryCriteria;
import com.funnycode.onlineshop.service.CategoryService;
import com.funnycode.onlineshop.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    ProductCategory productCategory;
    ProductCategoryCriteria productCategoryCriteria;
    CategoryRepository categoryRepository;
    ColorRepository colorRepository;
    BrandRepository brandRepository;

    @Override
    public List<ProductDTOResponse> getProductTrending() {
        return getRandomProductList(8);
    }

    @Override
    public List<ProductDTOResponse> getProductsBestSeller() {
        return getRandomProductList(8);
    }

    @Override
    public PagingDTOResponse searchProduct(ProductDTOFilter productDTOFilter) {
        return productCategoryCriteria.search(productDTOFilter);
    }

    @Override
    public ProductDTOResponse getProductById(int id) {
        Product product = productCategory.findById(id).orElseThrow(() -> OnlineShopException.notFoundException("Product does not exist"));
        return ProductMapper.toProductDTOResponse(product);
    }

    @Override
    public ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate) {
        validateProductDTOCreate(productDTOCreate);
        Category category = categoryRepository.findById(productDTOCreate.getCategoryId()).orElseThrow(()
                -> OnlineShopException.notFoundException("Category does not exist"));
        System.out.println(category.getCategoryName());
        Color color = colorRepository.findById(productDTOCreate.getColorId()).orElseThrow(()
                -> OnlineShopException.notFoundException("Color does not exist"));
        Brand brand = brandRepository.findById(productDTOCreate.getBrandId()).orElseThrow(()
                -> OnlineShopException.notFoundException("Brand does not exist"));
        Product product = productCategory.save(Product.builder()
                .name(productDTOCreate.getName())
                .price(productDTOCreate.getPrice())
                .quantity(productDTOCreate.getQuantity())
                .specification(productDTOCreate.getSpecification())
                .availability(productDTOCreate.getAvailability())
                .imageUrl(productDTOCreate.getImageUrl())
                .summary(productDTOCreate.getSummary())
                .description(productDTOCreate.getDescription())
                .category(category)
                .color(color)
                .brand(brand)
                .build());
        return ProductMapper.toProductDTOResponse(product);
    }

    private List<ProductDTOResponse> getRandomProductList(int end) {
        return productCategory.findAll().stream().map(ProductMapper::toProductDTOResponse).collect(Collectors.collectingAndThen(Collectors.toList(), collectedList -> {
            Collections.shuffle(collectedList);
            return collectedList.subList(0, Math.min(collectedList.size(), end));
        }));
    }

    private void validateProductDTOCreate(ProductDTOCreate productDTOCreate){
        if(productDTOCreate.getName() == null){
            throw OnlineShopException.badRequestException("Name must not be null");
        }
        if(productDTOCreate.getPrice()<=0){
            throw OnlineShopException.badRequestException("Price must be greater than 0");
        }
        if(productDTOCreate.getQuantity()<0){
            throw OnlineShopException.badRequestException("Price must be not be a minus number");
        }
        if(productDTOCreate.getAvailability() == null){
            throw OnlineShopException.badRequestException("Availability must not be null");
        }
    }

}
