package com.dAvor.service.imple;

import com.dAvor.exception.APIEXception;
import com.dAvor.exception.ResourceNotFoundException;
import com.dAvor.model.Category;
import com.dAvor.payload.CategoryDTO;
import com.dAvor.payload.CategoryResponse;
import com.dAvor.repository.CategoryRepository;
import com.dAvor.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImple implements CategoryService {


    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImple(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse allCategories(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryPage.getContent();
        if ( categories.isEmpty()) throw new APIEXception("There is no Category exist.");

        List<CategoryDTO> categoryDTOList = categories
                                                .stream()
                                                .map(category -> modelMapper.map(category, CategoryDTO.class))
                                                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setContent(categoryDTOList);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setIsLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {

        Category existingCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName().trim());
        if(existingCategory == null){

            Category category = modelMapper.map(categoryDTO, Category.class);
            Category savedCategory = categoryRepository.save(category);

            return modelMapper.map(savedCategory , CategoryDTO.class);
        }else{
            throw new APIEXception("Category with name "+ existingCategory.getCategoryName()+ " already exist.");
        }
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Optional<Category> optionalExistCategory = categoryRepository.findById(categoryId);

        Category existCategory = optionalExistCategory.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId) );


        categoryRepository.delete(existCategory);
        return modelMapper.map(existCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category savedCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName().trim());

        if(savedCategory != null){
            throw new APIEXception("Category with Id "+ savedCategory.getCategoryId() + " and name "+ savedCategory.getCategoryName()+ " already exist.");
        }

        Optional<Category> optionalExistCategory = categoryRepository.findById(categoryId);

        Category existCategory = optionalExistCategory.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId) );

        Category newCategory = modelMapper.map(categoryDTO, Category.class);

        existCategory.setCategoryName(newCategory.getCategoryName());
        Category updatedCategory = categoryRepository.save(existCategory);

        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }
}
