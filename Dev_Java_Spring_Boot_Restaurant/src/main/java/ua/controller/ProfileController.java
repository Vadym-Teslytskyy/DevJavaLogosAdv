package ua.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import ua.entity.User;
import ua.model.request.FileRequest;
import ua.model.request.UserProfileRequest;
import ua.service.FileWriter;
import ua.service.UserService;

@Controller
@RequestMapping("/profile/{id}")
@SessionAttributes("userProfile")
public class ProfileController {

	private final FileWriter writer;
	
	private final UserService service;

	@Autowired
	public ProfileController(FileWriter writer, UserService service) {
		this.writer = writer;
		this.service = service;
	}
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();
	
	@Value("${file.path}")
	private String path;
	
	@ModelAttribute("fileRequest")
	public FileRequest getFile() {
		return new FileRequest();
	}
	
	@ModelAttribute("userProfile")
	public UserProfileRequest getForm(){
		return new UserProfileRequest();
	}
	
	@GetMapping
	public String show(Model model, User user){
		model.addAttribute("user", user);
		model.addAttribute("meals", service.findAllTastedMeals(user.getId()));
		return "userProfile";
	}
	
	@PostMapping
	public String update(@ModelAttribute("userProfile") UserProfileRequest request, SessionStatus status, @ModelAttribute("fileRequest") FileRequest fileRequest) {
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
			service.update(request);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return cancel(status);
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/profile/{id}";
	}
	
}
