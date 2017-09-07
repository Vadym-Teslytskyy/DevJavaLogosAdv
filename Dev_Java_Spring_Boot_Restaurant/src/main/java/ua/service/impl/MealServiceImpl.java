package ua.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Meal;
import ua.model.request.MealRequest;
import ua.model.view.ComponentView;
import ua.model.view.MealView;
import ua.repository.MealRepository;
import ua.service.MealService;

@Service
public class MealServiceImpl implements MealService{
	
	private final MealRepository repository;

	@Autowired
	public MealServiceImpl(MealRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ComponentView> findAllComponents() {
		return repository.findAllComponents();
	}	

	@Override
	public List<String> findAllCuisines() {
		return repository.findAllCuisines();
	}
	
	@Override
	public List<MealView> findAllView() {
		return repository.findAllView();
	}

	@Override
	public void save(MealRequest request) {
		Meal meal = new Meal();
		meal.setId(request.getId());
		meal.setName(request.getName());
		meal.setFullDescription(request.getFullDescription());
		meal.setPrice(new BigDecimal(request.getPrice()));
		meal.setWeight(new Integer(request.getWeight()));
		meal.setCuisine(request.getCuisine());
		meal.setComponents(request.getComponents());
		repository.save(meal);
	}

	@Override
	public MealRequest findOneRequest(Integer id) {
		Meal meal = repository.findOneRequest(id);
		MealRequest request = new MealRequest();
		request.setId(meal.getId());
		request.setName(meal.getName());
		request.setFullDescription(meal.getFullDescription());
		request.setPrice(String.valueOf((meal.getPrice())));
		request.setWeight(String.valueOf((meal.getWeight())));
		request.setCuisine(meal.getCuisine());
		request.setComponents(meal.getComponents());
		return request;
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public Page<MealView> findAllView(Pageable pageable) {
		return repository.findAllView(pageable);
	}

}
