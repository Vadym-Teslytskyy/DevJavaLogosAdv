package ua.model.view;

import java.util.List;

import ua.entity.Meal;
import ua.entity.Place;

public class OrderView {
	
	private Integer id;
	
	private List<Meal> meals;
	
	private Place place;


	public OrderView(Integer id, List<Meal> meals, Place place) {
		this.id = id;
		this.meals = meals;
		this.place = place;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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