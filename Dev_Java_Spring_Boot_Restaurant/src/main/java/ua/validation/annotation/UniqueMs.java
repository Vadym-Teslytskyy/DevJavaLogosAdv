package ua.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.validation.validator.CuisineUniqueValidatior;
import ua.validation.validator.MsUniqueValidator;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = MsUniqueValidator.class)
public @interface UniqueMs {
	
	String message() default "Not unique";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}