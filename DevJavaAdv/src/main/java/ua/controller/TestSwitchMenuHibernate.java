package ua.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Cuisine;
import ua.entity.Meal;
import ua.model.view.ComponentView;
import ua.model.view.MealIndexView;
import ua.model.view.MealView;

public class TestSwitchMenuHibernate {
	private Scanner scanner = new Scanner(System.in);

	public void startMenu() {
		boolean isRun = true;
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("primary");
		while (isRun) {
			System.out.println("Меню дій: \n" 
					+ "Щоб додати кухню, введіть: 1 \n"
					+ "Щоб редагувати кухню по id, введіть: 2(Спершу введіть поля що хочете змінити, а потім id(номер рядка) для кого застосувати ці зміни); !якщо хочете залишити без змін поле перепешіть його \n"
					+ "Щоб видалити кухню, введіть: 3 \n"
					+ "Щоб додати страву, введіть: 4 \n"
					+ "Щоб редагувати страву по id, введіть: 5 \n"
					+ "Щоб видалити страву по id, введіть: 6 \n"
					+ "Щоб відкрити меню пошуку, введіть: 7 \n"
					+ "Щоб відкрити меню з рошриненими можливостями(агрегативні ф-ії), введіть: 8 \n"
					+ "Щоб відкрити меню виводу views, введіть: 9 \n"
					+ "Щоб відкрити меню пошуку по критеріях, введіть: 10 \n"
					+ "Щоб вийти з програми, введіть: 0");

				switch (scanner.next()) {
				case "1":
					addCuisien(factory);
					break;
				case "2":
					changeCuisien(factory);
					break;
				case "3":
					removeCuisien(factory);
					break;
				case "4":
					addMeal(factory);
					break;
				case "5":
					changeMeal(factory);
					break;
				case "6":
					removeMeal(factory);
					break;
				case "7":
					findMenu(factory);
					break;
				case "8":
					agregationMenu(factory);
					break;
				case "9":
					viewsMenu(factory);
					break;
				case "10":
				TestSwitchMenuFinder tFinder = new TestSwitchMenuFinder();
				tFinder.startMenu();
					break;
				case "0":
					isRun = false;
					break;
				default:
					System.out.println("Невірний вибір! Спробуйте ще раз!");
				}
			}
		
		factory.close();
	}
	
	private void addCuisien(EntityManagerFactory factory) {
		System.out.println("Введіть назву кухні:");
		String cuisineName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Cuisine cuisine = new Cuisine(cuisineName);
		em.persist(cuisine);
		em.getTransaction().commit();
		em.close();
	}
	
	private void changeCuisien(EntityManagerFactory factory) {
		System.out.println("Введіть індекс:");
		int index = scanner.nextInt();
		System.out.println("Введіть нову назву кухні:");
		String cuisineName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Cuisine cuisine = em.find(Cuisine.class, index);
		em.persist(cuisine);
		cuisine.setName(cuisineName);
		em.getTransaction().commit();
		em.close();
	}
	
	private void removeCuisien(EntityManagerFactory factory) {
		System.out.println("Введіть індекс:");
		int index = scanner.nextInt();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Cuisine cuisine = em.find(Cuisine.class, index);
		em.persist(cuisine);
		em.remove(cuisine);
		em.getTransaction().commit();
		em.close();
	}
	
	private void addMeal(EntityManagerFactory factory) {
		System.out.println("Введіть індекс кухні:");
		int index = scanner.nextInt();
		System.out.println("Введіть повний опис:");
		String fullDesc = scanner.next();
		System.out.println("Введіть назву страви:");
		String name = scanner.next();
		System.out.println("Введіть ціну:");
		String price = scanner.next();
		System.out.println("Введіть короткий опис:");
		String shortDesc = scanner.next();
		System.out.println("Введіть вагу страви:");
		int weight = scanner.nextInt();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Cuisine cuisine = em.find(Cuisine.class, index);
		em.persist(cuisine);
		Meal meal = new Meal();
		meal.setCuisine(cuisine);
		meal.setFullDescription(fullDesc);
		meal.setName(name);
		meal.setPrice(new BigDecimal(price));
		meal.setShortDescription(shortDesc);
		meal.setWeight(weight);
		em.persist(meal);
		em.getTransaction().commit();
		em.close();
	}
	
	private void changeMeal(EntityManagerFactory factory) {
		System.out.println("Введіть індекс страви:");
		int indexMeal = scanner.nextInt();
		System.out.println("Введіть індекс кухні:");
		int index = scanner.nextInt();
		System.out.println("Введіть повний опис:");
		String fullDesc = scanner.next();
		System.out.println("Введіть назву страви:");
		String name = scanner.next();
		System.out.println("Введіть ціну:");
		String price = scanner.next();
		System.out.println("Введіть короткий опис:");
		String shortDesc = scanner.next();
		System.out.println("Введіть вагу страви:");
		int weight = scanner.nextInt();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Cuisine cuisine = em.find(Cuisine.class, index);
		em.persist(cuisine);
		Meal meal = em.find(Meal.class, indexMeal);
		meal.setCuisine(cuisine);
		meal.setFullDescription(fullDesc);
		meal.setName(name);
		meal.setPrice(new BigDecimal(price));
		meal.setShortDescription(shortDesc);
		meal.setWeight(weight);
		em.persist(meal);
		em.getTransaction().commit();
		em.close();
	}
	
	private void removeMeal(EntityManagerFactory factory) {
		System.out.println("Введіть індекс страви:");
		int indexMeal = scanner.nextInt();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Meal meal = em.find(Meal.class, indexMeal);
		em.persist(meal);
		em.remove(meal);
		em.getTransaction().commit();
		em.close();
	}
	
	private void findMenu(EntityManagerFactory factory) {
		boolean isRun = true;
		while (isRun) {
			System.out.println("Меню пошуку: \n" 
					+ "Щоб шукати по назві страви, введіть: 1 \n"
					+ "Щоб шукати по початковій букві(чи підрявку), введіть: 2 \n"
					+ "Щоб шукати по назві кухні, введіть: 3 \n"
					+ "Щоб шукати по ціні, введіть: 4 \n"
					+ "Щоб шукати по діапазону ціни, введіть: 5 \n"
					+ "Щоб вийти з меню пошуку, введіть: 0");
			String input = scanner.next();
			switch (input) {
			case "1":
				findByName(factory);
				break;
			case "2":
				findBySubLine(factory);
				break;
			case "3":
				findByCuisineName(factory);
				break;
			case "4":
				findByPrice(factory);
				break;
			case "5":
				findByPriceRange(factory);
				break;
			case "0":
				isRun = false;
				break;
			default:
				System.out.println("Невірний вибір! Спробуйте ще раз!");
			}
		}
	}
	
	private void findByName(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("Введіть назву страви:");
		String inputName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Meal> list = em.createQuery("FROM Meal m WHERE m.name=?1",Meal.class)
				.setParameter(1, inputName)
				.getResultList();
		for (Meal meal : list) {
			count++;
			System.out.println("Meal #"+count
								+"\n Name: \n"+meal.getName()
									+"\n FullDesc: \n"+meal.getFullDescription()
										+"\n ShortDesc \n"+meal.getShortDescription()
											+"\n Price: \n"+meal.getPrice()
												+"\n Weight: \n"+meal.getWeight());
		}
		if (count == 0) {
			System.out.println("Не знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findBySubLine(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("Введіть початкову букву чи підрядок:");
		String inputName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Meal> list = em.createQuery("FROM Meal m WHERE m.name LIKE ?1",Meal.class)
				.setParameter(1, inputName+"%")
				.getResultList();
		for (Meal meal : list) {
			count++;
			System.out.println("Meal #"+count
					+"\n Name: \n"+meal.getName()
						+"\n FullDesc: \n"+meal.getFullDescription()
							+"\n ShortDesc \n"+meal.getShortDescription()
								+"\n Price: \n"+meal.getPrice()
									+"\n Weight: \n"+meal.getWeight());
		}
		if (count == 0) {
			System.out.println("Не знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findByCuisineName(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("Введіть назву кухні:");
		String inputName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Meal> list = em.createQuery("SELECT m FROM Meal m JOIN m.cuisine c WHERE c.name= ?1",Meal.class)
				.setParameter(1, inputName)
				.getResultList();
		for (Meal meal : list) {
			count++;
			System.out.println("Meal #"+count
								+"\n Name: \n"+meal.getName()
									+"\n FullDesc: \n"+meal.getFullDescription()
										+"\n ShortDesc \n"+meal.getShortDescription()
											+"\n Price: \n"+meal.getPrice()
												+"\n Weight: \n"+meal.getWeight());
		}
		if (count == 0) {
			System.out.println("Не знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findByPrice(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("Введіть ціну:");
		String inputName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Meal> list = em.createQuery("FROM Meal m WHERE m.price=?1",Meal.class)
				.setParameter(1, new BigDecimal(inputName))
				.getResultList();
		for (Meal meal : list) {
			count++;
			System.out.println("Meal #"+count
								+"\n Name: \n"+meal.getName()
									+"\n FullDesc: \n"+meal.getFullDescription()
										+"\n ShortDesc \n"+meal.getShortDescription()
											+"\n Price: \n"+meal.getPrice()
												+"\n Weight: \n"+meal.getWeight());
		}
		if (count == 0) {
			System.out.println("Не знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findByPriceRange(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("Введіть мінімальну ціну:");
		String minPrice = scanner.next();
		System.out.println("Введіть максимальну ціну:");
		String maxPrice = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Meal> list = em.createQuery("FROM Meal m WHERE m.price > ?1 AND m.price < ?2",Meal.class)
				.setParameter(1, new BigDecimal(minPrice))
				.setParameter(2, new BigDecimal(maxPrice))
				.getResultList();
		for (Meal meal : list) {
			count++;
			System.out.println("Meal #"+count
								+"\n Name: \n"+meal.getName()
									+"\n FullDesc: \n"+meal.getFullDescription()
										+"\n ShortDesc \n"+meal.getShortDescription()
											+"\n Price: \n"+meal.getPrice()
												+"\n Weight: \n"+meal.getWeight());
		}
		if (count == 0) {
			System.out.println("Не знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void agregationMenu(EntityManagerFactory factory) {
		boolean isRun = true;
		while (isRun) {
			System.out.println("Меню пошуку: \n" 
					+ "Щоб знайти максимальну ціну страви, введіть: 1 \n"
					+ "Щоб знайти мінімальну ціну страви, введіть: 2 \n"
					+ "Щоб знайти страви з ціною нижчою ніж середня арифметична, введіть: 3 \n"
					+ "Щоб знайти суму усіх цін: 4 \n"
					+ "Щоб к-сть усіх став: 5 \n"
					+ "Щоб вийти з меню, введіть: 0");
			String input = scanner.next();
			switch (input) {
			case "1":
				findMaxPrice(factory);
				break;
			case "2":
				findMinPrice(factory);
				break;
			case "3":
				findLessAvgOfPrice(factory);
				break;
			case "4":
				findSumOfPrice(factory);
				break;
			case "5":
				findCountOfPrice(factory);
				break;
			case "0":
				isRun = false;
				break;
			default:
				System.out.println("Невірний вибір! Спробуйте ще раз!");
			}
		}
	}
	
	private void findMaxPrice(EntityManagerFactory factory) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		BigDecimal max = em.createQuery("SELECT max(m.price) FROM Meal m",BigDecimal.class)
				.getSingleResult();
		System.out.println("Максимальна ціна: "+max);
		em.getTransaction().commit();
		em.close();
	}
	private void findMinPrice(EntityManagerFactory factory) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		BigDecimal min = em.createQuery("SELECT min(m.price) FROM Meal m",BigDecimal.class)
				.getSingleResult();
		System.out.println("Мінімальна ціна: "+min);
		em.getTransaction().commit();
		em.close();
	}
	private void findSumOfPrice(EntityManagerFactory factory) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		BigDecimal sum = em.createQuery("SELECT sum(m.price) FROM Meal m",BigDecimal.class)
				.getSingleResult();
		System.out.println("Сума усіх цін: "+sum);
		em.getTransaction().commit();
		em.close();
	}
	private void findCountOfPrice(EntityManagerFactory factory) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Long count = em.createQuery("SELECT count(m.id) FROM Meal m",Long.class)
				.getSingleResult();
		System.out.println("К-сть усіх страв: "+count);
		em.getTransaction().commit();
		em.close();
	}
	private void findLessAvgOfPrice(EntityManagerFactory factory) {
		int count = 0;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Double avg = em.createQuery("SELECT avg(m.price) FROM Meal m",Double.class)
				.getSingleResult();
		List<Meal> list = em.createQuery("FROM Meal m WHERE m.price=?1",Meal.class)
				.setParameter(1, new BigDecimal(avg))
				.getResultList();
		for (Meal meal : list) {
			count++;
			System.out.println("Meal #"+count
								+"\n Name: \n"+meal.getName()
									+"\n FullDesc: \n"+meal.getFullDescription()
										+"\n ShortDesc \n"+meal.getShortDescription()
											+"\n Price: \n"+meal.getPrice()
												+"\n Weight: \n"+meal.getWeight());
		}
		if (count == 0) {
			System.out.println("Не знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void viewsMenu(EntityManagerFactory factory) {
		boolean isRun=true;
		while (isRun) {
			System.out.println("Меню пошуку: \n" 
					+ "Щоб вивести MealView, введіть: 1 \n"
					+ "Щоб вивести MealIndexView, введіть: 2 \n"
					+ "Щоб вивести ComponentView, введіть: 3 \n"
					+ "Щоб вивести назви всіх кухонь, введіть: 4 \n"
					+ "Щоб вийти з меню, введіть: 0");
			String input = scanner.next();
			switch (input) {
			case "1":
				showMealView(factory);
				break;
			case "2":
				showMealIndexView(factory);
				break;
			case "3":
				showComponentView(factory);
				break;
			case "4":
				showAllCuisinesName(factory);
				break;
			case "0":
				isRun = false;
				break;
			default:
				System.out.println("Невірний вибір! Спробуйте ще раз!");
			}
		}
		
	}
	
	private void showMealView(EntityManagerFactory factory) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<MealView> views = em.createQuery("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.rate, m.name, m.fullDescription, m.price, m.weight, c.name) FROM Meal m JOIN m.cuisine c", MealView.class)
 				.getResultList();
		views.forEach(System.out::println);
		em.getTransaction().commit();
		em.close();
	}
	
	private void showMealIndexView(EntityManagerFactory factory) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<MealIndexView> views = em.createQuery("SELECT new ua.model.view. MealIndexView(m.id, m.photoUrl, m.version, m.rate, m.name, m.shortDescription) FROM Meal m", MealIndexView.class)
 				.getResultList();
		views.forEach(System.out::println);
		em.getTransaction().commit();
		em.close();
	}
	
	private void showComponentView(EntityManagerFactory factory) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<ComponentView> views = em.createQuery("SELECT new ua.model.view. ComponentView(String ingredient, BigDecimal amount, ms.name)) FROM ComponentView c JOIN c.ms ms", ComponentView.class)
 				.getResultList();
		views.forEach(System.out::println);
		em.getTransaction().commit();
		em.close();
	}
	
	private void showAllCuisinesName(EntityManagerFactory factory) {
		int count = 0;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<String> views = em.createQuery("SELECT c.name FROM Cuisine c", String.class)
 				.getResultList();
		for (String cuisineName : views) {
			count++;
			System.out.println("#"+count+" "+cuisineName);
		}
		em.getTransaction().commit();
		em.close();
	}
}
