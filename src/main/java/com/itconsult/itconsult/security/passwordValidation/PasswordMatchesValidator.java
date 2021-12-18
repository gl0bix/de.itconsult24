package com.itconsult.itconsult.security.passwordValidation;

import com.itconsult.itconsult.controller.form.CustomerRegisterFormModel;
import com.itconsult.itconsult.controller.form.ProviderRegisterFormModel;
import com.itconsult.itconsult.entity.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof CustomerRegisterFormModel) {
            CustomerRegisterFormModel c = (CustomerRegisterFormModel) obj;
            return c.getPassword().equals(c.getConfirmPassword());
        }
        if (obj instanceof ProviderRegisterFormModel) {
            ProviderRegisterFormModel p = (ProviderRegisterFormModel) obj;
            return p.getPassword().equals(p.getConfirmPassword());
        }


        return false;
    }
}
