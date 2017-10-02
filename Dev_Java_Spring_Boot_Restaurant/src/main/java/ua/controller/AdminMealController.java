package ua.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import ua.entity.User;
import ua.model.filter.MealFilter;
import ua.model.filter.SimpleFilter;
import ua.model.request.FileRequest;
import ua.model.request.MealRequest;
import ua.service.FileWriter;
import ua.service.MealService;

@Controller
@RequestMapping("/admin/meal")
@SessionAttributes("meal")
public class AdminMealController {
	
	private final FileWriter writer;
	
	private final MealService service;
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();
	
	@Value("${file.path}")
	private String path;
	
	
	@Autowired
	public AdminMealController(FileWriter writer, MealService service) {
		this.writer = writer;
		this.service = service;
	}
	
	@ModelAttribute("meal")
	public MealRequest getForm(){
		return new MealRequest();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@ModelAttribute("mealFilter")
	public MealFilter getMealFilter() {
		return new MealFilter();
	}
	
	@ModelAttribute("fileRequest")
	public FileRequest getFile() {
		return new FileRequest();
}
	
	@GetMapping
	public String show(Model model, User user, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter){
		model.addAttribute("user", user);
		model.addAttribute("cuisines", service.findAllCuisines());
		model.addAttribute("components", service.findAllComponents());
		model.addAttribute("ingredients", service.findAllIngredients());
		model.addAttribute("meals", service.findAll(filter, pageable));
		return "meal";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter) {
		service.delete(id);
		return "redirect:/admin/meal"+buildParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("meal") @Valid MealRequest request, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter, @ModelAttribute("fileRequest") FileRequest fileRequest, User user) {
		if(br.hasErrors()) 
			return show(model, user, pageable, filter);
		String photoUrl = writer.write(fileRequest.getFile());
		File toUpload = new File(path+photoUrl);
		try {
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(toUpload,
					ObjectUtils.asMap("use_filename", "true", "unique_filename", "false"));
			String cloudinaryUrl = (String) uploadResult.get("url");
			String oldPhotoUrl = request.getPhotoUrl();
			if ((oldPhotoUrl != null) && (oldPhotoUrl.equals(cloudinaryUrl))) {
				request.setVersion(request.getVersion() + 1);
			} else {
				request.setVersion(0);
			}
			request.setPhotoUrl(cloudinaryUrl);
			service.save(request);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return cancel(status, pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter, User user){
		model.addAttribute("meal", service.findOneRequest(id));
		return show(model, user, pageable, filter);
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("mealFilter") MealFilter filter){
		status.setComplete();
		return "redirect:/admin/meal"+buildParams(pageable, filter);
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
