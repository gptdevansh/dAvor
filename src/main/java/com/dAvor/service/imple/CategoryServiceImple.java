package com.dAvor.service.imple;

import com.dAvor.exception.APIEXception;
import com.dAvor.exception.ResourceNotFoundException;
import com.dAvor.model.Category;
import com.dAvor.repository.CategoryRepository;
import com.dAvor.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImple implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImple(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> allCategories() {
        List<Category> categories = categoryRepository.findAll();
        if ( categories.isEmpty()) throw new APIEXception("There is no Category exist.");
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        Category existingCategory =categoryRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory == null){
            categoryRepository.save(category);
        }else{
            throw new APIEXception("Category with name "+ existingCategory.getCategoryName()+ " already exist.");
        }
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Optional<Category> optionalExistCategory = categoryRepository.findById(categoryId);

        Category existCategory = optionalExistCategory.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId) );


        categoryRepository.delete(existCategory);
        return existCategory.getCategoryName() + " category deleted successfully.";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if(savedCategory != null){
            throw new APIEXception("Category with Id "+ savedCategory.getCategoryId() + " and name "+ savedCategory.getCategoryName()+ " already exist.");
        }

        Optional<Category> optionalExistCategory = categoryRepository.findById(categoryId);

        Category existCategory = optionalExistCategory.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId) );

        existCategory.setCategoryName(category.getCategoryName());

        return categoryRepository.save(existCategory);
    }
}
