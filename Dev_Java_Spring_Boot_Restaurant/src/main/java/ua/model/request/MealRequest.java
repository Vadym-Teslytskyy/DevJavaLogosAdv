package ua.model.request;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import ua.entity.Component;
import ua.entity.Cuisine;
import ua.validation.annotation.UniqueMeal;

public class MealRequest {
	
	private Integer id;
	
	@UniqueMeal(message="Така страва вже існує")
	@NotBlank(message="Поле не може бути пустим")
	@Pattern(regexp = "^[A-Z][A-Za-z0-9]+| *$", message="Назва має починатись з великої букви")
	private String name;
	
	@NotBlank(message="Поле не може бути пустим")
	private String fullDescription;
	
	@NotBlank(message="Поле не може бути пустим")
	private String price;

	@NotBlank(message="Поле не може бути пустим")
	private String weight;
	
	private Cuisine cuisine;
	
	List<Component> components;
	
	private String photoUrl;
	
	private int version;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}
	
	
}
