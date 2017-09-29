package ua.entity;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Index;

@Entity
@Table(name="place", indexes= @Index(columnList = "number", unique=true))
public class Place extends AbstractEntity{
	
	private int countofPeople;
	
	private int number;
	
	private boolean isFree;
	
	@OneToOne(fetch=FetchType.LAZY)
	private User userWhoReserved;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Order order;

	public Place() {
	}
	
	public Place(int countofPeople, int number) {
		this.countofPeople = countofPeople;
		this.number = number;
		this.isFree = true;
	}
	
	public User getUserWhoReserved() {
		return userWhoReserved;
	}

	public void setUserWhoReserved(User userWhoReserved) {
		this.userWhoReserved = userWhoReserved;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
	
//	@PostConstruct
//	private void isFreeFlag(){
//		if (userWhoReserved!=null) {
//			isFree = false;
//		}else {
//			isFree = true;
//		}
//	}
}
