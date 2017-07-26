package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cuisine")
public class Cuisine extends AbstractEntity{
	
	private String name;
	
	@OneToMany(mappedBy="cuisine")
	private List<Meal> meals = new ArrayList<>();
	
	

	public Cuisine() {
	}
	

	public Cuisine(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
