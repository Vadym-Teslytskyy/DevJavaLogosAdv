package ua.service;

import java.util.List;

import ua.model.request.RegistrationRequest;
import ua.model.request.UserProfileRequest;
import ua.model.view.MealView;

public interface UserService {

	void save(RegistrationRequest request);
	
	List<MealView> findAllTastedMeals(Integer userId);

	void update(UserProfileRequest request);

}
