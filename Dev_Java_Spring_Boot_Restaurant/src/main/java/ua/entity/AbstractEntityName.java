package ua.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.annotation.UniqueCuisine;
import ua.validation.annotation.UniqueIngredient;
import ua.validation.annotation.UniqueMs;
import ua.validation.flag.CuisineFlag;
import ua.validation.flag.IngredientFlag;
import ua.validation.flag.MsFlag;

@MappedSuperclass
public abstract class AbstractEntityName extends AbstractEntity{
	
	@UniqueMs(message="Така вимірювальна система вже існує", groups=MsFlag.class)
	@UniqueCuisine(message="Така кухня вже існує", groups=CuisineFlag.class)
	@UniqueIngredient(message="Такий інгредієнт вже існує", groups=IngredientFlag.class)
	@NotBlank(message="Поле не може бути пустим", groups= {IngredientFlag.class, CuisineFlag.class, MsFlag.class})
	@Pattern(regexp = "^[A-Z][A-Za-z0-9]+| *$", message="Назва має починатись з великої букви", groups= {IngredientFlag.class, CuisineFlag.class})
	private String name;

	public AbstractEntityName(String name) {
		this.name = name;
	}

	public AbstractEntityName() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
}
}
