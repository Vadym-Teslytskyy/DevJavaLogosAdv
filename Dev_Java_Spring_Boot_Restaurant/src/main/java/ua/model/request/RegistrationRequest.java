package ua.model.request;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.annotation.PasswordsEqualConstraint;
import ua.validation.annotation.UniqueEmail;

@PasswordsEqualConstraint(message="Пароль не однаковий!")
public class RegistrationRequest {
	
	@UniqueEmail(message="Такий користувач з таким email вже існує")
	@NotBlank(message="Поле не можебути пустим")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Некоректний email")
	private String email;
	
	@NotBlank(message="Поле не може бути пустим")
	private String password;
	
	@NotBlank(message="Поле не може бути пустим")
	private String repeatPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
}
