package com.funnycode.onlineshop.controller;

import com.funnycode.onlineshop.dto.CategoryDTOResponse;
import com.funnycode.onlineshop.dto.CategoryDTOCreate;
import com.funnycode.onlineshop.dto.CategoryDTOUpdate;
import com.funnycode.onlineshop.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.funnycode.onlineshop.util.Constant.API_VERSION;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(API_VERSION + "/categories")
@CrossOrigin
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public CategoryDTOResponse createCategory(@RequestBody CategoryDTOCreate categoryDTOCreate) {
        return categoryService.createCategory(categoryDTOCreate);
    }

    @GetMapping
    public List<CategoryDTOResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTOResponse getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryDTOResponse updateCategory(@PathVariable int id, @RequestBody CategoryDTOUpdate categoryDTOUpdate) {
        return categoryService.updateCategory(id, categoryDTOUpdate);
    }

    @DeleteMapping("/{id}")
    public CategoryDTOResponse deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }
}
