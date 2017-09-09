package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.UserRepository;
import ua.validation.annotation.UniqueEmail;

@Component
public class EmailUniquevalidator implements ConstraintValidator<UniqueEmail, String>{
	
	private final UserRepository repository;

	public EmailUniquevalidator(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueEmail arg0) {	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (repository.findByEmail(value))==null;
	}

}
