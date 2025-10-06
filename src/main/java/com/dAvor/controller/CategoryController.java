package com.dAvor.controller;

import com.dAvor.model.Category;
import com.dAvor.service.CategoryService;
import com.dAvor.service.imple.CategoryServiceImple;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/public/categories")
    ResponseEntity<List<Category>> allCategories(){
        return new ResponseEntity<>(categoryService.allCategories(), HttpStatus.OK);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/public/categories")
//    List<Category> allCategories(){
//        return categoryServiceImple.allCategories();
//    }



    @PostMapping("/admin/categories")
    ResponseEntity<Map<String,String>> addCategory(@Valid @RequestBody Category category){
        categoryService.addCategory(category);
        return new ResponseEntity<>(Map.of("message", "Category added successfully. "), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long categoryId ){

            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(Map.of("message", status), HttpStatus.OK);

    }

    @PutMapping("/admin/categories/{categoryId}")
    ResponseEntity<Map<String, String>> updateCategory(@Valid @RequestBody Category category,@Valid @PathVariable Long categoryId){

            Category updatedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>( Map.of("message", updatedCategory.getCategoryId() + " has updated."), HttpStatus.OK);

    }
}
