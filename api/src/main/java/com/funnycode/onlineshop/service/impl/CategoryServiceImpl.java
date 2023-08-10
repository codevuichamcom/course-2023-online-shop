package com.funnycode.onlineshop.service.impl;

import com.funnycode.onlineshop.entity.Category;
import com.funnycode.onlineshop.dto.CategoryDTOCreate;
import com.funnycode.onlineshop.dto.CategoryDTOResponse;
import com.funnycode.onlineshop.dto.CategoryDTOUpdate;
import com.funnycode.onlineshop.exception.OnlineShopException;
import com.funnycode.onlineshop.mapper.CategoryMapper;
import com.funnycode.onlineshop.repository.CategoryRepository;
import com.funnycode.onlineshop.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryDTOResponse createCategory(CategoryDTOCreate categoryDTOCreate) {
        Category category = categoryRepository.save(CategoryMapper.toCategory(categoryDTOCreate));
        return CategoryMapper.toCategoryDTOResponse(category);
    }

    @Override
    public List<CategoryDTOResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTOResponse getCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> OnlineShopException.notFoundException("Category does not exist"));
        return CategoryMapper.toCategoryDTOResponse(category);
    }

    @Override
    public CategoryDTOResponse updateCategory(int id, CategoryDTOUpdate categoryDTOUpdate) {
        if (!categoryRepository.existsById(id)) {
            throw OnlineShopException.notFoundException("Category does not exist");
        }
        Category category = CategoryMapper.toCategory(categoryDTOUpdate);
        category.setId(id);
        return CategoryMapper.toCategoryDTOResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryDTOResponse deleteCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> OnlineShopException.notFoundException("Category does not exist"));
        categoryRepository.delete(category);
        return CategoryMapper.toCategoryDTOResponse(category);
    }
}
