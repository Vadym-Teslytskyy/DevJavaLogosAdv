package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.model.request.RegistrationRequest;
import ua.validation.annotation.PasswordsEqualConstraint;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordsEqualConstraint, Object>{

	@Override
	public void initialize(PasswordsEqualConstraint arg0) {	}

	@Override
	public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
		RegistrationRequest user = (RegistrationRequest) candidate;
		return user.getPassword().equals(user.getRepeatPassword());
	}

}
