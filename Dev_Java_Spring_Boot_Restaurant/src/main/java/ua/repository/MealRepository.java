package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Meal;
import ua.model.view.ComponentView;
import ua.model.view.MealView;

public interface MealRepository extends JpaNameRepository<Meal>, JpaSpecificationExecutor<MealView>{
	
	@Query("SELECT new ua.model.view.ComponentView(c.id, i.name, c.amount, ms.name) FROM Component c JOIN c.ingredient i JOIN c.ms ms")
	List<ComponentView> findAllComponents();

	@Query("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.rate, m.name, m.fullDescription, m.price, m.weight, c.name) FROM Meal m JOIN m.cuisine c")
	List<MealView> findAllView();
	
	@Query(value="SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.rate, m.name, m.fullDescription, m.price, m.weight, c.name) FROM Meal m JOIN m.cuisine c",
			countQuery="SELECT count(m.id) FROM Meal m JOIN m.cuisine c")
	Page<MealView> findAllView(Pageable pageable);

	@Query("SELECT c.name FROM Cuisine c")
	List<String> findAllCuisines();
	
	@Query("SELECT i.name FROM Ingredient i")
	List<String> findAllIngredients();

	@Query("SELECT m FROM Meal m JOIN FETCH m.cuisine WHERE m.id=?1")
	Meal findOneRequest(Integer id);
	
}
