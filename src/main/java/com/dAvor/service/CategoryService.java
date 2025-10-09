package com.dAvor.service;

import com.dAvor.model.Category;
import com.dAvor.payload.CategoryDTO;
import com.dAvor.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse allCategories(Integer pageNumbe, Integer pageSize);
    CategoryDTO addCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
