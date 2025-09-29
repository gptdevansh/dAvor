package com.dAvor.service.imple;

import com.dAvor.model.Category;
import com.dAvor.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryServiceImple implements CategoryService {

    List<Category> categories = new LinkedList<>();

    @Override
    public List<Category> allCategories() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }
}
