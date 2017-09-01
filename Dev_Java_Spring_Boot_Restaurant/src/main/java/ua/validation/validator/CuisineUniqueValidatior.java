package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.CusineRepository;
import ua.validation.annotation.UniqueCuisine;

@Component
public class CuisineUniqueValidatior implements ConstraintValidator<UniqueCuisine, String>{
	
	private final CusineRepository repository;
	
	public CuisineUniqueValidatior(CusineRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueCuisine constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !repository.existsByName(value);
	}
	
	

}
