package ua.model.view;

import java.time.LocalDateTime;
import java.util.List;

import ua.entity.OrderStatus;
import ua.entity.Place;

public class OrderView {
	
	private Integer id;
	
	private LocalDateTime time;
	
	private List<MealView> meals;
	
	private Place place;
	
	private OrderStatus status;
	

	public OrderView(Integer id, Place place, OrderStatus status, LocalDateTime time) {
		this.id = id;
		this.place = place;
		this.status = status;
		this.time = time;
	}

	
	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<MealView> getMeals() {
		return meals;
	}

	public void setMeals(List<MealView> meals) {
		this.meals = meals;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}


	public LocalDateTime getTime() {
		return time;
	}


	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
	
}
