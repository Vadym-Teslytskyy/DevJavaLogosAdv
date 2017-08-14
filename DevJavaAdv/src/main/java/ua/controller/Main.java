package ua.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.entity.Component;
import ua.entity.Component_;
import ua.entity.Cuisine;
import ua.entity.Cuisine_;
import ua.entity.Ingredient;
import ua.entity.Ingredient_;
import ua.entity.Meal;
import ua.entity.Meal_;
import ua.entity.Place;
import ua.model.view.MealView;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("primary");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Place place = new Place(4, 1, true);
		Place place1 = new Place(3, 2, true);
		Place place2 = new Place(4, 3, true);
		Place place3 = new Place(2, 4, true);
//		Cuisine cuisine = new Cuisine("Ukrainian");
		em.persist(place);
		em.persist(place1);
		em.persist(place2);
		em.persist(place3);
//		Cuisine cuisine1 = em.find(Cuisine.class, 1);
//		Meal meal = new Meal();
//		meal.setCuisine(cuisine1);
//		meal.setFullDescription("Full desc");
//		meal.setName("Sausage");
//		meal.setPrice(new BigDecimal("80"));
//		meal.setShortDescription("Short desc");
//		meal.setWeight(200);
//		em.persist(meal);
//		List<Meal> list = em.createQuery("FROM Meal m WHERE m.name=?1",Meal.class)
//				.setParameter(1, "Sausage")
//				.getResultList();
//		for (Meal meal : list) {
//			System.out.println(meal.getName());
//		}
		
//		List<Meal> meals = em.createQuery("SELECT m FROM Meal m JOIN FETCH m.cuisine c WHERE c.name=?1 AND m.components IS NOT EMPTY",Meal.class)
//				.setParameter(1, "Ukrainian")
//				.getResultList();
//		List<MealView> views = em.createQuery("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.rate, m.name, m.fullDescription, m.price, m.weight, c.name) FROM Meal m JOIN m.cuisine c WHERE c.name=?1", MealView.class)
//								.setParameter(1, "German")
//				 				.getResultList();
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<MealView> cq = cb.createQuery(MealView.class);
//		Root<Meal> root = cq.from(Meal.class);
//		Join<Meal, Cuisine> cuisineJoin = root.join(Meal_.cuisine);
//		Join<Meal,Component> componentJoin = root.join(Meal_.components);
//		Join<Component, Ingredient> ingredientJoin = componentJoin.join(Component_.ingredient);
//		cq.multiselect(root.get(Meal_.id),
//				root.get(Meal_.photoUrl),
//				root.get(Meal_.version),
//				root.get(Meal_.rate),
//				root.get(Meal_.name),
//				root.get(Meal_.fullDescription),
//				root.get(Meal_.price),
//				root.get(Meal_.weight),
//				cuisineJoin.get(Cuisine_.name));
//		Predicate ratePredicate = cb.ge(root.get(Meal_.rate), new BigDecimal("4"));
//		Predicate namePredicate = cb.like(root.get(Meal_.name), "A%");
//		Predicate cuisinePredicate = cuisineJoin.get(Cuisine_.name).in(Arrays.asList("Ukrainian", "German"));
//		Predicate ingredientPredicate = ingredientJoin.get(Ingredient_.name).in(Arrays.asList("potato", "meat", "root"));
//		cq.where(ratePredicate, namePredicate, cuisinePredicate, ingredientPredicate);
////		Fetch<Meal, Cuisine> fetch = root.fetch(Meal_.cuisine);
////		Join<Meal, Cuisine> join = (Join<Meal, Cuisine>) fetch;
//		List<MealView> meals = em.createQuery(cq).getResultList();
//System.out.println(meals);
		em.getTransaction().commit();
		em.close();
//		"SELECT c.name FROM Cuisine c" запит на вивід всіх name of Cusine
//				meals.forEach(m->System.out.println(m.getCuisine().getName()));
//				views.forEach(System.out::println);
		factory.close();
	}

}
