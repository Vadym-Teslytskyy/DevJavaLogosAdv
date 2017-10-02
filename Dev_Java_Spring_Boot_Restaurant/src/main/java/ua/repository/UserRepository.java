package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.User;
import ua.model.view.MealView;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	
	@Query("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.rate, m.name, m.fullDescription, m.price, m.weight, c.name) FROM Meal m JOIN m.cuisine c JOIN m.users u WHERE u.id=?1")
	List<MealView> findAllTastedMeals(Integer userId);

}
