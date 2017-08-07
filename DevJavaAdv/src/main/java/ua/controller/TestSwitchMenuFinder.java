package ua.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
import ua.model.view.MealView;

public class TestSwitchMenuFinder {
	private Scanner scanner = new Scanner(System.in);
	private List<Predicate> predicates = new ArrayList<>();
	
 
	public void startMenu() {
		boolean isRun = true;
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("primary");
		EntityManager em = factory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MealView> cq = cb.createQuery(MealView.class);
		Root<Meal> root = cq.from(Meal.class);
		Join<Meal, Cuisine> cuisineJoin = root.join(Meal_.cuisine);
		em.getTransaction().begin();
		
		while (isRun) {
			System.out.println("Чи важлива назва страви: \n"
					+ "Якщо так, введіть 1 \n"
					+ "Якщо ні, введіть 2 \n");
			String input = scanner.next();
			switch (input) {
			case "1":
				nameFilter(factory, predicates, em, cb, cq, root, cuisineJoin);
				break;
			case "2":
				break;
			default:
				break;
			}
			System.out.println("Чи важлива ціна страви: \n"
					+ "Якщо так, введіть 1 \n"
					+ "Якщо ні, введіть 2 \n");
			input = scanner.next();
			switch (input) {
			case "1":
				priceFilter(factory, predicates, em, cb, cq, root, cuisineJoin);
				break;
			case "2":
				break;
			default:
				break;
			}
			System.out.println("Чи важливий рейтинг страви: \n"
					+ "Якщо так, введіть 1 \n"
					+ "Якщо ні, введіть 2 \n");
			input = scanner.next();
			switch (input) {
			case "1":
				rateFilter(factory, predicates, em, cb, cq, root, cuisineJoin);
				break;
			case "2":
				break;
			default:
				break;
			}
			System.out.println("Чи важлива кухня страви: \n"
					+ "Якщо так, введіть 1 \n"
					+ "Якщо ні, введіть 2 \n");
			input = scanner.next();
			switch (input) {
			case "1":
				cuisineNameFilter(factory, predicates, em, cb, cq, root, cuisineJoin);
				break;
			case "2":
				break;

			default:
				break;
			}
			System.out.println("Чи важливі компоненти страви: \n"
					+ "Якщо так, введіть 1 \n"
					+ "Якщо ні, введіть 2 \n");
			input = scanner.next();
			switch (input) {
			case "1":
				ingredientsFilter(factory, predicates, em, cb, cq, root, cuisineJoin);
				break;
			case "2":
				isRun = false;
			default:
				break;
			}
		}
		
		creatingWhereBlock(factory, predicates, em, cb, cq, root, cuisineJoin);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	
	private void nameFilter(EntityManagerFactory factory,
								List<Predicate> predicates,
									EntityManager em,
										CriteriaBuilder cb,
											CriteriaQuery<MealView> cq,
												Root<Meal> root,
													Join<Meal, Cuisine> cuisineJoin) {
		System.out.println("Введіть шаблон:");
		String inputPatternFilter = scanner.next();
		cq.multiselect(root.get(Meal_.id),
				root.get(Meal_.photoUrl),
				root.get(Meal_.version),
				root.get(Meal_.rate),
				root.get(Meal_.name),
				root.get(Meal_.fullDescription),
				root.get(Meal_.price),
				root.get(Meal_.weight),
				cuisineJoin.get(Cuisine_.name));
		Predicate namePredicate = cb.like(root.get(Meal_.name), inputPatternFilter+"%");
		predicates.add(namePredicate);
	}
	
	private void priceFilter(EntityManagerFactory factory,
								List<Predicate> predicates,
									EntityManager em,
										CriteriaBuilder cb,
											CriteriaQuery<MealView> cq,
												Root<Meal> root,
													Join<Meal, Cuisine> cuisineJoin) {
		System.out.println("Введіть на яку найбільшу суму ви розраховуєте:");
		String inputPriceFilter = scanner.next();
		cq.multiselect(root.get(Meal_.id),
				root.get(Meal_.photoUrl),
				root.get(Meal_.version),
				root.get(Meal_.rate),
				root.get(Meal_.name),
				root.get(Meal_.fullDescription),
				root.get(Meal_.price),
				root.get(Meal_.weight),
				cuisineJoin.get(Cuisine_.name));
		Predicate pricePredicate = cb.le(root.get(Meal_.price), new BigDecimal(inputPriceFilter));
		predicates.add(pricePredicate);
	}
	
	private void rateFilter(EntityManagerFactory factory,
								List<Predicate> predicates,
									EntityManager em,
										CriteriaBuilder cb,
											CriteriaQuery<MealView> cq,
												Root<Meal> root,
													Join<Meal, Cuisine> cuisineJoin) {
		System.out.println("Введіть найменший ретинг на який ви прозраховуєте:");
		String inputRateFilter = scanner.next();
		cq.multiselect(root.get(Meal_.id),
				root.get(Meal_.photoUrl),
				root.get(Meal_.version),
				root.get(Meal_.rate),
				root.get(Meal_.name),
				root.get(Meal_.fullDescription),
				root.get(Meal_.price),
				root.get(Meal_.weight),
				cuisineJoin.get(Cuisine_.name));
		Predicate ratePredicate = cb.ge(root.get(Meal_.rate), new BigDecimal(inputRateFilter));
		predicates.add(ratePredicate);
	}
	
	private void cuisineNameFilter(EntityManagerFactory factory,
										List<Predicate> predicates,
											EntityManager em, CriteriaBuilder cb,
												CriteriaQuery<MealView> cq,
													Root<Meal> root,
														Join<Meal, Cuisine> cuisineJoin) {
		System.out.println("Скільки кухонь ви хочете ввести?");
		int amount = scanner.nextInt();
		String[] cuisineNameArray = new String[amount+1];
		for (int i = 0; i < cuisineNameArray.length; i++) {
			System.out.println("Введіть назву кухні що бажаєте скуштувати:");
			String cusineName = scanner.next();
			cuisineNameArray[i] = cusineName;
		}
		cq.multiselect(root.get(Meal_.id),
				root.get(Meal_.photoUrl),
				root.get(Meal_.version),
				root.get(Meal_.rate),
				root.get(Meal_.name),
				root.get(Meal_.fullDescription),
				root.get(Meal_.price),
				root.get(Meal_.weight),
				cuisineJoin.get(Cuisine_.name));
		Predicate cuisinePredicate = cuisineJoin.get(Cuisine_.name).in(Arrays.asList(cuisineNameArray));
		predicates.add(cuisinePredicate);
	}
	
	private void ingredientsFilter(EntityManagerFactory factory,
										List<Predicate> predicates,
											EntityManager em,
												CriteriaBuilder cb,
													CriteriaQuery<MealView> cq,
														Root<Meal> root,
															Join<Meal, Cuisine> cuisineJoin) {
		System.out.println("Скільки інгредієнтів ви хочете ввести?");
		int amount = scanner.nextInt();
		String[] ingredientArray = new String[amount+1];
		for (int i = 0; i < ingredientArray.length; i++) {
			System.out.println("Введіть назву кухні що бажаєте скуштувати:");
			String ingredient = scanner.next();
			ingredientArray[i] = ingredient;
		}
		Join<Meal,Component> componentJoin = root.join(Meal_.components);
		Join<Component, Ingredient> ingredientJoin = componentJoin.join(Component_.ingredient);
		cq.multiselect(root.get(Meal_.id),
				root.get(Meal_.photoUrl),
				root.get(Meal_.version),
				root.get(Meal_.rate),
				root.get(Meal_.name),
				root.get(Meal_.fullDescription),
				root.get(Meal_.price),
				root.get(Meal_.weight),
				cuisineJoin.get(Cuisine_.name));
		Predicate ingredientPredicate = ingredientJoin.get(Ingredient_.name).in(Arrays.asList(ingredientArray));
		predicates.add(ingredientPredicate);
	}
	
	private void creatingWhereBlock(EntityManagerFactory factory,
										List<Predicate> predicates,
											 EntityManager em, CriteriaBuilder cb,
											 	CriteriaQuery<MealView> cq,
											 		Root<Meal> root,Join<Meal,
														 Cuisine> cuisineJoin) {
		int count = 0;
		if (predicates.size()>0) {
			Predicate[] predicates2 = new Predicate[predicates.size()];		
			cq.multiselect(root.get(Meal_.id),
					root.get(Meal_.photoUrl),
					root.get(Meal_.version),
					root.get(Meal_.rate),
					root.get(Meal_.name),
					root.get(Meal_.fullDescription),
					root.get(Meal_.price),
					root.get(Meal_.weight),
					cuisineJoin.get(Cuisine_.name));
			
			cq.where(predicates.toArray(predicates2));
			List<MealView> meals = em.createQuery(cq).getResultList();
			
			for (MealView meal : meals) {
				count++;
				System.out.println("Meal #"+count
									+"\n Name: \n"+meal.getName()
										+"\n FullDesc: \n"+meal.getFullDescription()
												+"\n Price: \n"+meal.getPrice()
													+"\n Weight: \n"+meal.getWeight()
														+"\n Cuisine: \n"+meal.getCuisine());
			}
			if (count == 0) {
				System.out.println("Не знайдено страв(и) з таким параметром!");
			}
		}
		
	}
}
