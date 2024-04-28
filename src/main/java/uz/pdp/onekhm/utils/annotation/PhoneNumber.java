package uz.pdp.onekhm.utils.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.onekhm.utils.annotation.validation.PhoneNumberValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidation.class)
public @interface PhoneNumber {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
