package com.dAvor.controller;

import com.dAvor.model.Category;
import com.dAvor.service.imple.CategoryServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryServiceImple categoryServiceImple;

    public void setCategoryServiceImple(CategoryServiceImple categoryServiceImple) {
        this.categoryServiceImple = categoryServiceImple;
    }

    @GetMapping("/public/categories")
    ResponseEntity<List<Category>> allCategories(){
        return new ResponseEntity<>(categoryServiceImple.allCategories(), HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    ResponseEntity<String> addCategory(@RequestBody Category category){
        categoryServiceImple.addCategory(category);
        return new ResponseEntity<>("Category added successfully ... ", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    ResponseEntity<String> deleteCategory(@PathVariable Long categoryId ){
        try{
            String status =  categoryServiceImple.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity(e.getMessage(), e.getStatusCode());
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
        try{
            Category updatedCategory = categoryServiceImple.updateCategory(category, categoryId);
            return new ResponseEntity<>( updatedCategory.getCategoryId() + " has updated.", HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
