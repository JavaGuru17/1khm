package uz.pdp.onekhm.utils.annotation;

import jakarta.validation.Constraint;
import uz.pdp.onekhm.utils.annotation.validation.LinkValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LinkValidation.class)
public @interface Link {
}