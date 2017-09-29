package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.User;
import ua.model.filter.SimpleFilter;
import ua.service.PlaceService;

@Controller
@RequestMapping("/places")
public class PlacesMenuController {

	private final PlaceService service;
	
	@Autowired
	public PlacesMenuController(PlaceService service) {
		this.service = service;
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}
	
	@GetMapping
	public String show(Model model, User user, @PageableDefault Pageable pageable,	@ModelAttribute("filter") SimpleFilter filter){
		if(user!=null){
			model.addAttribute("message", "View profile "+user.getEmail());
		}else {
			model.addAttribute("message", "Hello unregistrated user");
		}
		model.addAttribute("places", service.findAllView(pageable, filter));
		return "places";
	}
	
}
