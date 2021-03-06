package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.MealFilter;
import ua.model.filter.SimpleFilter;
import ua.model.request.MealRequest;
import ua.model.view.ComponentView;
import ua.model.view.MealIndexView;
import ua.model.view.MealView;

public interface MealService {

	List<ComponentView> findAllComponents();
	
	List<ComponentView> findComponentsOfMeal(Integer id);

	List<MealView> findAllView();

	List<String> findAllCuisines();

	void save(MealRequest request);
	
	MealRequest findOneRequest(Integer id);
	
	void delete(Integer id);
	
	List<String> findAllIngredients();

	Page<MealView> findAllView(Pageable pageable);

	Page<MealView> findAllView(Pageable pageable, SimpleFilter filter);
	
	Page<MealIndexView> findAll(MealFilter filter, Pageable pageable);
	
	List<MealIndexView> find5MealsByRate();	
	
	MealView findById(Integer id);
	
}
