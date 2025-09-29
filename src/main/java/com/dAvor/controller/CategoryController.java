package com.dAvor.controller;

import com.dAvor.model.Category;
import com.dAvor.service.imple.CategoryServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryServiceImple categoryServiceImple;

    public void setCategoryServiceImple(CategoryServiceImple categoryServiceImple) {
        this.categoryServiceImple = categoryServiceImple;
    }

    @GetMapping("/admin/categories")
    List<Category> allCategories(){
        return categoryServiceImple.allCategories();
    }

    @PostMapping("/admin/categories")
    String addCategory(@RequestBody Category category){
        categoryServiceImple.addCategory(category);
        return "Category added successfully ... ";
    }
}
