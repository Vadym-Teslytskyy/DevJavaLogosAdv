package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.PlaceRepository;
import ua.validation.annotation.UniquePlace;

@Component
public class PlaceUniqueValidator implements ConstraintValidator<UniquePlace, String>{
	
	private final PlaceRepository repository;
	
	public PlaceUniqueValidator(PlaceRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniquePlace arg0) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			return repository.findByNumber(Integer.valueOf(value))==null;
		} catch (NumberFormatException e) {
			return true;
		}
	}

}
