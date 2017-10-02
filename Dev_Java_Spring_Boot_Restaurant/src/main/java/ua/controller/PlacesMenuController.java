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
import org.springframework.web.bind.annotation.PathVariable;

import ua.entity.User;
import ua.model.filter.SimpleFilter;
import ua.service.PlaceService;

@Controller
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
	
	@GetMapping("/places")
	public String show(Model model, User user, @PageableDefault Pageable pageable){
		if(user!=null){
			model.addAttribute("message", "View profile "+user.getEmail());
		}else {
			model.addAttribute("message", "Hello unregistrated user");
		}
		model.addAttribute("user", user);
		model.addAttribute("places", service.findAllView(pageable));
		return "places";
	}
	
	@GetMapping("/places/{id}")
	public String updateUserId( @PathVariable Integer id, Model model, User user) {
		service.updateUserId(user.getId(), id);
	 return "redirect:/places/{id}/order";
	}
	
	@GetMapping("/places/{id}/cancel")
	public String cancelAllReservations( @PathVariable Integer id, Model model, User user, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter) {
		service.cancelAllReservations(user.getId(), id);
	 return "redirect:/places"+buildParams(pageable, filter);
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
