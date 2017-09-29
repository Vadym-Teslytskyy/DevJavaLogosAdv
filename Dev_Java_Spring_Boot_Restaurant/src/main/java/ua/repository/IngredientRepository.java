package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Ingredient;
import ua.model.view.IngredientView;
import ua.model.view.MealView;

public interface IngredientRepository extends JpaNameRepository<Ingredient>, JpaSpecificationExecutor<Ingredient>{

	@Query(value="SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.rate, m.name, m.fullDescription, m.price, m.weight, c.name) FROM Meal m JOIN m.cuisine c JOIN m.components com JOIN com.ingredient i WHERE i.id=?1",
			countQuery="SELECT count(m.id) FROM Meal m JOIN m.components c JOIN c.ingredient i WHERE i.id=?1")
	Page<MealView> findMealsViewOfIngredient(Integer id, Pageable pageable);
	
	@Query(value="SELECT new ua.model.view.IngredientView(i.id, i.name) FROM Ingredient i",
			countQuery="SELECT count(i.id) FROM Ingredient i")
	Page<IngredientView> findAllView(Pageable pageable);
	
	@Query("SELECT new ua.model.view.IngredientView(i.id, i.name) FROM Ingredient i WHERE i.id=?1")
	IngredientView findViewById(Integer id);
}
