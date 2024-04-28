package uz.pdp.onekhm.utils.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.onekhm.utils.annotation.Link;

public class LinkValidation implements ConstraintValidator<Link, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.startsWith("https") || s.startsWith("http"))
            return true;
        throw new IllegalArgumentException("Please provide a valid URL");
    }
}
