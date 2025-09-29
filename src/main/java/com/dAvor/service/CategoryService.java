package com.dAvor.service;

import com.dAvor.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> allCategories();
    void addCategory(Category category);
}
