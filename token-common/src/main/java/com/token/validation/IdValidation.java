package com.token.validation;

import com.token.annotation.Id;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdValidation implements ConstraintValidator<Id, Long> {
    @Override
    public boolean isValid(Long Id, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("555");
        if (Id == null) {
            return false;
        }
        return true;
    }
}
