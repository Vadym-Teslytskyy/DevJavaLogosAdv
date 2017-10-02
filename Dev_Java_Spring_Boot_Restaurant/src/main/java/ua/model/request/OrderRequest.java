package ua.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import ua.entity.Meal;
import ua.entity.OrderStatus;
import ua.entity.Place;

public class OrderRequest {

	private Integer id;
	
	private OrderStatus status;
	
	@NotNull(message="Виберіть хоч 1 страву для замовлення")
	private List<Meal> meals;
	
	//@NotNull(message="Зарезервуйте столик перед замовленням")
	private Place place;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
	
	
}
