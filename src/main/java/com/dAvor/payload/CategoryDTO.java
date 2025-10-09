package com.dAvor.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {


    //    @NotBlank(message = "Category name can't blank.")
    private Long categoryId;

    private String categoryName;



}
