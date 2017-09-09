package ua.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.validation.validator.ComponentUniqueValidator;

@Retention(RUNTIME)
@Constraint(validatedBy = ComponentUniqueValidator.class)
public @interface UniqueComponent {
	
	String message() default "Not unique";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
