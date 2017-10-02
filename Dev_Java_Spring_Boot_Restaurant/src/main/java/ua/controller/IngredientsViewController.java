package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.User;
import ua.service.IngredientService;

@Controller
@RequestMapping("/ingredient/{id}")
public class IngredientsViewController {
	
	private final IngredientService service;
	
	@Autowired
	public IngredientsViewController(IngredientService service) {
		this.service = service;
	}

	@GetMapping
	public String ingredientView(Model model, User user,@PathVariable Integer id, @PageableDefault Pageable pageable) {
		if(user!=null){
			model.addAttribute("message", "View profile "+user.getEmail());
		}else {
			model.addAttribute("message", "Hello unregistrated user");
		}
		model.addAttribute("user", user);
		model.addAttribute("meals", service.findMealsOfIngredient(id, pageable));
		model.addAttribute("ingredient", service.findById(id));
		return "ingredientView";
	}
}
