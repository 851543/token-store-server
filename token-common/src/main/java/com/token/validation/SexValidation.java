package com.token.validation;

import com.token.annotation.Sex;
import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 注解实现性别校验
 */
public class SexValidation implements ConstraintValidator<Sex, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        if (s.equals("男") || s.equals("女")) {
            return true;
        }
        return false;
    }
}
