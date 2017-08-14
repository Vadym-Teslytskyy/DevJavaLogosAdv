package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.Cuisine;
import ua.service.CusineService;

@Controller
@RequestMapping("/admin/cuisine")
public class AdminCuisineController {
	
	private final CusineService service;
	
	@Autowired
	public AdminCuisineController(CusineService service) {
		this.service = service;
	}
	
	@GetMapping
	public String show(Model model) {
		model.addAttribute("cuisines", service.findAll());
		return "cuisine";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/admin/cuisine";
	}
	
	@PostMapping
	public String save(@RequestParam String name){
		service.save(new Cuisine(name));
		return "redirect:/admin/cuisine";
	}
	
	

}
