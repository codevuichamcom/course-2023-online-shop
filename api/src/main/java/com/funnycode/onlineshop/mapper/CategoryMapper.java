package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.entity.Category;
import com.funnycode.onlineshop.dto.CategoryDTOCreate;
import com.funnycode.onlineshop.dto.CategoryDTOResponse;
import com.funnycode.onlineshop.dto.CategoryDTOUpdate;

public class CategoryMapper {
    public static CategoryDTOResponse toCategoryDTOResponse(Category category) {
        return CategoryDTOResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    public static Category toCategory(CategoryDTOCreate categoryDTOCreate) {
        return Category.builder()
                .categoryName(categoryDTOCreate.getCategoryName())
                .build();
    }

    public static Category toCategory(CategoryDTOUpdate categoryDTOUpdate) {
        return Category.builder()
                .categoryName(categoryDTOUpdate.getCategoryName())
                .build();
    }
}
