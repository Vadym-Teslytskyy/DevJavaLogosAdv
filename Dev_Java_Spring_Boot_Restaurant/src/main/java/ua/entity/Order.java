package ua.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="_order")
public class Order extends AbstractEntity{
	
	private LocalDateTime time;
	
	private OrderStatus status;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Place place;
	
	@ManyToMany
	private List<Meal> meals = new ArrayList<>();
	
	public Order() {
	}
	
	public Order(OrderStatus status, Place place, List<Meal> meals) {
		this.status = status;
		this.place = place;
		this.meals = meals;
	}

	public LocalDateTime getTime() {
		return time;
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time;
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
