package uz.pdp.onekhm.utils.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.onekhm.utils.annotation.PhoneNumber;

import java.util.List;

public class PhoneNumberValidation implements ConstraintValidator<PhoneNumber, Object> {
    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null)
            return true;
        if (o instanceof String) {
            if (((String) o).matches("\\+998\\d{9}"))
                return true;
        } else if (o instanceof List<?>) {
            List<String> list = (List<String>) o;
            if (list.isEmpty())
                return true;
            for (String s : list){
                if (!s.matches("\\+998\\d{9}"))
                    throw new IllegalArgumentException("Invalid phone number (" + s + ")");
            }
        }
        throw new IllegalArgumentException("Invalid phone number");
    }
}
