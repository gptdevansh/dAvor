package com.dAvor.exception;

import lombok.Getter;
import org.springframework.data.repository.query.Param;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;


/*
    we can define two diff. fields for diff types but the best efficient sol. is use of Object as a type which cosist all types
    String fieldStringValue;
    Long fieldNumericValue;
*/

    private Object fieldValue;

    public ResourceNotFoundException() {
    }


    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
