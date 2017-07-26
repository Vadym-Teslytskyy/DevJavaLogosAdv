package ua.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Cuisine;
import ua.entity.Meal;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("primary");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
//		Cuisine cuisine = new Cuisine("Ukrainian");
//		em.persist(cuisine);
//		Cuisine cuisine1 = em.find(Cuisine.class, 1);
//		Meal meal = new Meal();
//		meal.setCuisine(cuisine1);
//		meal.setFullDescription("Full desc");
//		meal.setName("Sausage");
//		meal.setPrice(new BigDecimal("80"));
//		meal.setShortDescription("Short desc");
//		meal.setWeight(200);
//		em.persist(meal);
		List<Meal> list = em.createQuery("FROM Meal m WHERE m.name=?1",Meal.class)
				.setParameter(1, "Sausage")
				.getResultList();
		for (Meal meal : list) {
			System.out.println(meal.getName());
		}
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}