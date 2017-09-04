package ua.model.request;

import org.hibernate.validator.constraints.NotBlank;

import ua.entity.Ingredient;
import ua.entity.Ms;

public class ComponentRequest {
	
	private Integer id;
	
	private Ingredient ingredient;
	
	@NotBlank(message="Поле не може бути пустим")
	private String amount;
	
	private Ms ms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Ms getMs() {
		return ms;
	}

	public void setMs(Ms ms) {
		this.ms = ms;
	}
	
	

}
