package ua.service.classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.entity.Component;
import ua.entity.Component_;
import ua.entity.Cuisine;
import ua.entity.Ingredient;
import ua.entity.Meal;
import ua.entity.Meal_;
import ua.model.filter.MealFilter;

public class MealFilterPredicateBuilder {
	
	private final CriteriaBuilder cb;
	
	private final Root<Meal> root;
	
	private final MealFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();

	public MealFilterPredicateBuilder(CriteriaBuilder cb, Root<Meal> root, MealFilter filter) {
		this.cb = cb;
		this.root = root;
		this.filter = filter;
	}
	
	private void findByMinRate(){
		if(!filter.getMinRate().isEmpty()) {
							predicates.add(cb.ge(root.get("rate"), new BigDecimal(filter.getMinRate().replace(',', '.'))));
						}
	}
	
	private void findByMaxRate() {
					if(!filter.getMaxRate().isEmpty()) {
						predicates.add(cb.le(root.get("rate"), new BigDecimal(filter.getMaxRate().replace(',', '.'))));
					}
				}
	private void findByMinPrice(){
		if (!filter.getMinPrice().isEmpty()) {
			predicates.add(cb.ge(root.get("price"), new BigDecimal(filter.getMinPrice().replace(',', '.'))));
		}
	}
	
	private void findByMaxPrice() {
		if (!filter.getMaxPrice().isEmpty()) {
			predicates.add(cb.le(root.get("price"), new BigDecimal(filter.getMaxPrice().replace(',', '.'))));
		}
	}
	
	private void findByMinWeight() {
		if (!filter.getMinWeight().isEmpty()) {
			predicates.add(cb.ge(root.get("weight"), new Integer(filter.getMinWeight())));
		}
	}
	
	private void findByMaxWeight() {
		if (!filter.getMaxWeight().isEmpty()) {
			predicates.add(cb.le(root.get("weight"), new Integer(filter.getMaxWeight())));
		}
	}
				
	private	void findBySearch() {
					if(!filter.getSearch().isEmpty()) {
						predicates.add(cb.like(root.get("name"), filter.getSearch()+"%"));
					}
				}
				
	private void findByCusinesId() {
					if(!filter.getCuisinesIds().isEmpty()) {
						Join<Meal, Cuisine> join = root.join(Meal_.cuisine);
						predicates.add(join.get("name").in(filter.getCuisinesIds()));
					}
				}
	private void findByIngredientsIds() {
		if (!filter.getIngredientIds().isEmpty()) {
			Join<Meal, Component> componentJoin = root.join(Meal_.components);
			Join<Component, Ingredient> ingredientJoin = componentJoin.join(Component_.ingredient);
			predicates.add(ingredientJoin.get("name").in(filter.getIngredientIds()));
		}
	}
				
	public Predicate toPredicate() {
					findByMinRate();
					findByMaxRate();
					findByMinPrice();
					findByMaxPrice();
					findByMinWeight();
					findByMaxWeight();
					findBySearch();
					findByCusinesId();
					findByIngredientsIds();
					return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
				}
}

	
