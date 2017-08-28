package ua.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.entity.User;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index(Model model, Principal principal, SomeUserClass sClass) {
		if(principal!=null){
			model.addAttribute("message", "Hello "+principal.getName());
		}else {
			model.addAttribute("message", "Hello unregistrated user");
		}
		System.out.println(sClass.getUser());
		
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	static class SomeUserClass {
		private User user;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
	}

}
