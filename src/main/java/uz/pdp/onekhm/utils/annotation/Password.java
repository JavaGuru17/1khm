package uz.pdp.onekhm.utils.annotation;

import jakarta.validation.Constraint;
import uz.pdp.onekhm.utils.annotation.validation.PasswordValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidation.class)
public @interface Password {
}
