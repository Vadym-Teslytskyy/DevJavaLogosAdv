package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.MsRepository;
import ua.validation.annotation.UniqueMs;

@Component
public class MsUniqueValidator implements ConstraintValidator<UniqueMs, String>{
	
	private final MsRepository repositiry;
	
	public MsUniqueValidator(MsRepository repositiry) {
		this.repositiry = repositiry;
	}

	@Override
	public void initialize(UniqueMs constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !repositiry.existsByName(value);
	}

}
