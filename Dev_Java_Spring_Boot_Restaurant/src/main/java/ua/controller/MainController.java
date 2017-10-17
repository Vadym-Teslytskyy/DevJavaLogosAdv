package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ua.entity.User;
import ua.service.MealService;

@Controller
public class MainController {
	
	private final MealService service;
	
	@Autowired
	public MainController(MealService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String index(Model model, User user, @PageableDefault Pageable pageable) {
		if(user!=null){
			model.addAttribute("message", "View profile "+user.getEmail());
		}else {
			model.addAttribute("message", "Hello unregistrated user");
		}
		model.addAttribute("user", user);
		model.addAttribute("meals", service.find5MealsByRate());
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(Model model, User user) {
		model.addAttribute("user", user);
		return "admin";
	}
	
	@GetMapping("/meal/{id}")
	public String mealView(Model model, User user, @PathVariable Integer id){
		if(user!=null){
			model.addAttribute("message", "View profile "+user.getEmail());
		}else {
			model.addAttribute("message", "Hello unregistrated user");
		}
		model.addAttribute("user", user);
		model.addAttribute("components", service.findComponentsOfMeal(id));
		model.addAttribute("meal", service.findById(id));
		return "mealView";
	}
	
}
