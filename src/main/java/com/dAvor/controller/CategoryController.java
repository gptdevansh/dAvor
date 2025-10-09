package com.dAvor.controller;


import com.dAvor.payload.CategoryDTO;
import com.dAvor.payload.CategoryResponse;
import com.dAvor.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    ResponseEntity<CategoryResponse> allCategories(@RequestParam("pageNumber")Integer pageNumber, @RequestParam("pageSize")Integer pageSize){
        return new ResponseEntity<>(categoryService.allCategories(pageNumber,  pageSize), HttpStatus.OK);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/public/categories")
//    List<Category> allCategories(){
//        return categoryServiceImple.allCategories();
//    }



    @PostMapping("/admin/categories")
    ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId ){

            CategoryDTO deleteCategoryDTO = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deleteCategoryDTO, HttpStatus.OK);

    }

    @PutMapping("/admin/categories/{categoryId}")
    ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId){

            CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>( updatedCategoryDTO, HttpStatus.OK);

    }
}
