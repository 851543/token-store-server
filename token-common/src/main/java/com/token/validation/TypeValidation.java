package com.token.validation;

import com.token.annotation.Type;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TypeValidation implements ConstraintValidator<Type,Integer> {
    @Override
    public boolean isValid(Integer type, ConstraintValidatorContext constraintValidatorContext) {
        if(type == 1 || type == 0){
            return true;
        }
        return false;
    }
}
