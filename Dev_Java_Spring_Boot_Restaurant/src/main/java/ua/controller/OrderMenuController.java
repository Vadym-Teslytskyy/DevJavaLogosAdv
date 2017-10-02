package ua.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.User;
import ua.model.filter.SimpleFilter;
import ua.model.request.OrderRequest;
import ua.service.MealService;
import ua.service.OrderService;

@Controller
@RequestMapping("/places/{id}/order")
@SessionAttributes("order")
public class OrderMenuController {

	private final OrderService service;
	
	private final MealService mealService;

	public OrderMenuController(OrderService service, MealService mealService) {
		this.service = service;
		this.mealService = mealService;
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}
	
	@ModelAttribute("order")
	public OrderRequest getForm() {
		return new OrderRequest();
	}
	
	@GetMapping
	public String show(Model model, User user, @PageableDefault Pageable pageable,	@ModelAttribute("filter") SimpleFilter filter){
		if(user!=null){
			model.addAttribute("message", "View profile "+user.getEmail());
		}else {
			model.addAttribute("message", "Hello unregistrated user");
		}
		model.addAttribute("user", user);
		model.addAttribute("meals", service.findAllMealsName());
		model.addAttribute("place", user.getPlace());
		model.addAttribute("orders", service.findAllView(user.getPlace().getId(), pageable));
		return "order";
	}
	
	@PostMapping
	public String save(@PathVariable Integer id, @ModelAttribute("order") @Valid OrderRequest request,
			BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, User user, @ModelAttribute("filter") SimpleFilter filter) {
//		if (br.hasErrors())
//			return show(model, user, pageable, filter);
		if (!request.getMeals().isEmpty())
			service.saveOrder(request, user);
		return "redirect:/places/{id}/order";
		}
	
	private String buildParams(Pageable pageable, SimpleFilter filter) {
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
