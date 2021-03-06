package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="meal")
public class Meal extends AbstractEntityName{
	
	public Meal() {
	}
	
	public Meal(String name, String fullDescription, BigDecimal price, int weight, Cuisine cuisine,
			List<Component> components) {
		super(name);
		this.fullDescription = fullDescription;
		this.price = price;
		this.weight = weight;
		this.cuisine = cuisine;
		this.components = components;
	}

	private String photoUrl;

	private int version;

	private BigDecimal rate;

	private String fullDescription;
	
	private String shortDescription;
	
	private BigDecimal price;
	
	private int weight;
	
	private BigDecimal sumOfRate;
	
	private int countOfVoted;
	
	@ManyToMany(mappedBy="tastedMeals")
	private List<User> users;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cuisine cuisine;
	
	@ManyToMany(mappedBy="meals")
	private List<Order> orders = new ArrayList<>();
	
	@ManyToMany
	private List<Component> components = new ArrayList<>();
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public BigDecimal getSumOfRate() {
		return sumOfRate;
	}

	public void setSumOfRate(BigDecimal sumOfRate) {
		this.sumOfRate = sumOfRate;
	}

	public int getCountOfVoted() {
		return countOfVoted;
	}

	public void setCountOfVoted(int countOfVoted) {
		this.countOfVoted = countOfVoted;
	}

	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<Component> getComponents() {
		return components;
	}
	public void setComponents(List<Component> components) {
		this.components = components;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Cuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}
	
}
