package ua.service;

import java.util.List;

import ua.entity.Component;
import ua.entity.Meal;
import ua.model.view.ComponentView;
import ua.model.view.MealView;

public interface MealService extends CrudService<Meal, Integer>{

	List<ComponentView> findAllComponents();

	List<MealView> findAllView();

	List<String> findAllCuisines();
	
	

}
