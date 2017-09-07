package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.request.MealRequest;
import ua.model.view.ComponentView;
import ua.model.view.MealView;

public interface MealService {

	List<ComponentView> findAllComponents();

	List<MealView> findAllView();

	List<String> findAllCuisines();

	void save(MealRequest request);
	
	MealRequest findOneRequest(Integer id);
	
	void delete(Integer id);

	Page<MealView> findAllView(Pageable pageable);
	
}
