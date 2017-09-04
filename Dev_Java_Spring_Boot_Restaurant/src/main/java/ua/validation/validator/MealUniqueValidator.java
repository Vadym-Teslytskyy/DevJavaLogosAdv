package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.MealRepository;
import ua.validation.annotation.UniqueMeal;

@Component
public class MealUniqueValidator implements ConstraintValidator<UniqueMeal, String>{
	
	private final MealRepository repository;
	
	public MealUniqueValidator(MealRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueMeal constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !repository.existsByName(value);
	}

}
