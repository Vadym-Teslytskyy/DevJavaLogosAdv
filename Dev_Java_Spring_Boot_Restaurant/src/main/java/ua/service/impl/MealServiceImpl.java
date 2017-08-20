package ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Component;
import ua.entity.Meal;
import ua.model.view.ComponentView;
import ua.model.view.MealView;
import ua.repository.MealRepository;
import ua.service.MealService;

@Service
public class MealServiceImpl extends CrudServiceImpl<Meal, Integer> implements MealService{
	
	private final MealRepository repository;

	@Autowired
	public MealServiceImpl(MealRepository repository) {
		super(repository);
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

}
