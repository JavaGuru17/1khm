package uz.pdp.onekhm.utils.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.onekhm.utils.annotation.Length;

public class LengthValidation implements ConstraintValidator<Length, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        if (s.length() > 3 && s.length() < 100)
            return true;
        throw new IllegalArgumentException("Length should be between 3 and 100 characters");
    }
}
