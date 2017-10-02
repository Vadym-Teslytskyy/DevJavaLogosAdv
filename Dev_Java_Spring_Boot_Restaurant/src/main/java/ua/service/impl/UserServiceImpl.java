package ua.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.Role;
import ua.entity.User;
import ua.model.request.RegistrationRequest;
import ua.model.request.UserProfileRequest;
import ua.model.view.MealView;
import ua.repository.UserRepository;
import ua.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository repository;
	
	private final PasswordEncoder encoder;
	
	public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Override
	public void save(RegistrationRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRole(Role.ROLE_CLIENT);
		repository.save(user);
	}
	@PostConstruct
	public void createAdmin(){
		User userCheck = repository.findByEmail("admin");
		if (userCheck==null) {
			User user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			repository.save(user);
		}
	}

	@Override
	public List<MealView> findAllTastedMeals(Integer userId) {
		return repository.findAllTastedMeals(userId);
	}

	@Override
	public void update(UserProfileRequest request) {
		User user = repository.findOne(request.getId());
		user.setPhotoUrl(request.getPhotoUrl());
		user.setVersion(request.getVersion());
		repository.save(user);
	}
}
