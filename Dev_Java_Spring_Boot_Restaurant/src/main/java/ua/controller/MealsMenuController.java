package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.model.filter.MealFilter;
import ua.service.MealService;

@Controller
@RequestMapping("/meals")
public class MealsMenuController {
	
	private final MealService service;
	
	@Autowired
	public MealsMenuController(MealService service) {
		this.service = service;
	}
	
	@ModelAttribute("mealFilter")
	public MealFilter getMealFilter() {
		return new MealFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter){
		model.addAttribute("cuisines", service.findAllCuisines());
		model.addAttribute("ingredients", service.findAllIngredients());
		model.addAttribute("meals", service.findAll(filter, pageable));
		return "meals";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter){
		status.setComplete();
		return "redirect:/meals"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, MealFilter filter) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(filter.getSearch());
		return buffer.toString();
 	}
	

}
