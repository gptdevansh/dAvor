package com.dAvor.repository;

import com.dAvor.model.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByCategoryName(@NotBlank(message = "Category name can't blank.") String categoryName);
}
