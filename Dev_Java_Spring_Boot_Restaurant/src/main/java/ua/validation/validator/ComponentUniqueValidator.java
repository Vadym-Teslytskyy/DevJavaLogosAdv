package ua.validation.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.model.request.ComponentRequest;
import ua.repository.ComponentRepository;
import ua.validation.annotation.UniqueComponent;

public class ComponentUniqueValidator implements ConstraintValidator<UniqueComponent, ComponentRequest>{
	
	private final ComponentRepository repository;

	public ComponentUniqueValidator(ComponentRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueComponent arg0) {	}

	@Override
	public boolean isValid(ComponentRequest request, ConstraintValidatorContext context) {
		return (repository.existsByComponent(request.getIngredient(), new BigDecimal(request.getAmount()), request.getMs()))==null;
	}

}
