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
import org.springframework.web.bind.annotation.RequestMapping;

import ua.model.filter.SimpleFilter;
import ua.service.IngredientService;

@Controller
@RequestMapping("/ingredients")
public class IngredientsMenuController {
	
	private final IngredientService service;

	@Autowired
	public IngredientsMenuController(IngredientService service) {
		this.service = service;
	}
	
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter) {
		model.addAttribute("ingredients", service.findAll(pageable,filter));
		if(service.findAll(pageable, filter).hasContent()||pageable.getPageNumber()==0){
			return "ingredient";
		}else return "redirect:/admin/ingredient"+buildParams(pageable, filter);
		
	}
	//дописати dto для інгредієнта та змін в цьому методі перехід на це dto по id
	@GetMapping("/ingredients/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter) {
		return "redirect:/ingredients/{id}"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, SimpleFilter filter) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		if(!(service.findAll(pageable, filter).hasContent())) buffer.append(String.valueOf(pageable.getPageNumber()));
		else buffer.append(String.valueOf(pageable.getPageNumber()+1));
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