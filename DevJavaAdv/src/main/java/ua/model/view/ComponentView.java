package ua.model.view;

import java.math.BigDecimal;

public class ComponentView {

	private String ingredient;
	private BigDecimal amount;
	
	private String ms;
	
	

	public ComponentView(String ingredient, BigDecimal amount, String ms) {
		this.ingredient = ingredient;
		this.amount = amount;
		this.ms = ms;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	@Override
	public String toString() {
		return "ComponentView [ingredient=" + ingredient + ", amount=" + amount + ", ms=" + ms + "]";
	}
	
	
}
