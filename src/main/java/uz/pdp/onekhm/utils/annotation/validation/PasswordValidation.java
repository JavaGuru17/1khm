package uz.pdp.onekhm.utils.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.onekhm.utils.annotation.Password;

public class PasswordValidation implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null)
            return true;
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"))
            return true;
        throw new IllegalArgumentException("Password should contain at least one upper case letter, one lower case letter, one digit and length should be at least 8 characters long");
    }
}
