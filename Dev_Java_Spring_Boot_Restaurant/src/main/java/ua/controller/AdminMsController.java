package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Ms;
import ua.entity.User;
import ua.model.filter.SimpleFilter;
import ua.service.MsService;
import ua.validation.flag.MsFlag;

@Controller
@RequestMapping("/admin/ms")
@SessionAttributes("ms")
public class AdminMsController {

	private final MsService service;

	@Autowired
	public AdminMsController(MsService service) {
		this.service = service;
	}
	
	@ModelAttribute("ms")
	public Ms getForm(){
		return new Ms();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@GetMapping
	public String show(Model model, User user, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter) {
		model.addAttribute("user", user);
		model.addAttribute("mss", service.findAll(pageable,filter));
		return "ms";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter) {
		service.delete(id);
		return "redirect:/admin/ms"+buildParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("ms") @Validated(MsFlag.class) Ms ms, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter, User user){
		if(br.hasErrors()) return show(model,user,pageable,filter);
		service.save(ms);
		return cancel(status,pageable,filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter, User user){
		model.addAttribute("ms", service.findOne(id));
		return show(model,user,pageable,filter);
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		status.setComplete();
		return "redirect:/admin/ms"+buildParams(pageable, filter);
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
