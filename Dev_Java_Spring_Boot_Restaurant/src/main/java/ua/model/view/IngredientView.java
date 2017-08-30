package ua.model.view;

import java.util.List;

import ua.entity.Component;

public class IngredientView {

	private Integer id;
	
	private String name;
	
	private List<Component> components;

	public IngredientView(Integer id, String name, List<Component> components) {
		this.id = id;
		this.name = name;
		this.components = components;
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

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}
	
	
}
