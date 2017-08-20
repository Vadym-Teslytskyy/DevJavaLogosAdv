package ua.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.Component;
import ua.entity.Cuisine;
import ua.entity.Meal;
import ua.model.view.ComponentView;
import ua.repository.ComponentRepository;
import ua.repository.CusineRepository;
import ua.service.MealService;

@Controller
@RequestMapping("/admin/meal")
public class AdminMealController {
	
	private final MealService service;
	
	
	@Autowired
	private  ComponentRepository componentRepository;
	@Autowired
	private  CusineRepository  cuisineRepository;
	
	@Autowired
	public AdminMealController(MealService service) {
		this.service = service;
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("components", service.findAllComponents());
		model.addAttribute("cuisines", service.findAllCuisines());
		model.addAttribute("meals", service.findAllView());
		return "meal";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/admin/meal";
	}
	
	@PostMapping
	public String save(@RequestParam String name, 
						@RequestParam String fullDescription,
						@RequestParam String cuisine,
						@RequestParam BigDecimal price,
						@RequestParam Integer weight,
						@RequestParam List<Integer> idComponents) {
		Cuisine cuisine2 = cuisineRepository.findByName(cuisine);
		List<Component> components2 = componentRepository.findAll(idComponents);
		Meal meal = new Meal(name, fullDescription, price, weight, cuisine2, components2);
		service.save(meal);
		return "redirect:/admin/meal";
	}
	

	
	

}
