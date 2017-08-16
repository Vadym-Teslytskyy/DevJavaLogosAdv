package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="place")
public class Place extends AbstractEntity{
	
	public Place() {
	}
	

	public Place(int countofPeople, int number) {
		this.countofPeople = countofPeople;
		this.number = number;
		this.isFree = true;
	}



	private int countofPeople;
	
	private int number;
	
	private boolean isFree;
	
	@OneToMany(mappedBy="place")
	private List<Order> orders = new ArrayList<>();
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}

	public int getCountofPeople() {
		return countofPeople;
	}

	public void setCountofPeople(int countofPeople) {
		this.countofPeople = countofPeople;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
