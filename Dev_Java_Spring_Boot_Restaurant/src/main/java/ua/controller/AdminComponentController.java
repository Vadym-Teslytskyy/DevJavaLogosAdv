package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import ua.model.request.ComponentRequest;
import ua.service.ComponentService;

@Controller
@RequestMapping("/admin/component")
@SessionAttributes("component")
public class AdminComponentController {
	
	private final ComponentService service;
		
		@Autowired
		public AdminComponentController(ComponentService service) {
			this.service = service;
		}
		
		@ModelAttribute("component")
		public ComponentRequest getForm(){
			return new ComponentRequest();
		}
		
		@ModelAttribute("filter")
		public SimpleFilter getFilter(){
			return new SimpleFilter();
		}
		
		@GetMapping
		public String show(Model model, User user ,@PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter) {
			model.addAttribute("user", user);
			model.addAttribute("ingredients", service.findAllIngredients());
			model.addAttribute("mss", service.findAllMss());
			model.addAttribute("components", service.findAllView(pageable, filter));
			return "component";
		}
		
		@GetMapping("/delete/{id}")
		public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter) {
			service.delete(id);
			return "redirect:/admin/component"+buildParams(pageable, filter);
		}
		
		@PostMapping
		public String save(@ModelAttribute("component") @Valid ComponentRequest request, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter, User user) {
			if (br.hasErrors()) return show(model, user, pageable, filter);
			service.save(request);
			return cancel(status,pageable,filter);
		}
		
		@GetMapping("/update/{id}")
		public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter, User user){
			model.addAttribute("component", service.findOneRequest(id));
			return show(model, user, pageable, filter);
		}
		
		@GetMapping("/cancel")
		public String cancel(SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
			status.setComplete();
			return "redirect:/admin/component"+buildParams(pageable, filter);
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
