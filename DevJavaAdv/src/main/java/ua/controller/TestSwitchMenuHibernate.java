package ua.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.enterprise.inject.New;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Cuisine;
import ua.entity.Meal;

public class TestSwitchMenuHibernate {
	private Scanner scanner = new Scanner(System.in);

	public void startMenu() {
		boolean isRun = true;
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("primary");
		while (isRun) {
			System.out.println("ћеню д≥й: \n" 
					+ "ўоб додати кухню, введ≥ть: 1 \n"
					+ "ўоб редагувати кухню по id, введ≥ть: 2(—першу введ≥ть пол€ що хочете зм≥нити, а пот≥м id(номер р€дка) дл€ кого застосувати ц≥ зм≥ни); !€кщо хочете залишити без зм≥н поле перепеш≥ть його \n"
					+ "ўоб видалити кухню, введ≥ть: 3 \n"
					+ "ўоб додати страву, введ≥ть: 4 \n"
					+ "ўоб редагувати страву по id, введ≥ть: 5 \n"
					+ "ўоб видалити страву по id, введ≥ть: 6 \n"
					+ "ўоб в≥дкрити меню пошуку, введ≥ть: 7 \n"
					+ "ўоб вийти з програми, введ≥ть: 0");

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
				case "0":
					isRun = false;
					break;
				default:
					System.out.println("Ќев≥рний виб≥р! —пробуйте ще раз!");
				}
			}
		
		factory.close();
	}
	
	private void addCuisien(EntityManagerFactory factory) {
		System.out.println("¬вед≥ть назву кухн≥:");
		String cuisineName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Cuisine cuisine = new Cuisine(cuisineName);
		em.persist(cuisine);
		em.getTransaction().commit();
		em.close();
	}
	
	private void changeCuisien(EntityManagerFactory factory) {
		System.out.println("¬вед≥ть ≥ндекс:");
		int index = scanner.nextInt();
		System.out.println("¬вед≥ть нову назву кухн≥:");
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
		System.out.println("¬вед≥ть ≥ндекс:");
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
		System.out.println("¬вед≥ть ≥ндекс кухн≥:");
		int index = scanner.nextInt();
		System.out.println("¬вед≥ть повний опис:");
		String fullDesc = scanner.next();
		System.out.println("¬вед≥ть назву страви:");
		String name = scanner.next();
		System.out.println("¬вед≥ть ц≥ну:");
		String price = scanner.next();
		System.out.println("¬вед≥ть короткий опис:");
		String shortDesc = scanner.next();
		System.out.println("¬вед≥ть вагу страви:");
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
		System.out.println("¬вед≥ть ≥ндекс страви:");
		int indexMeal = scanner.nextInt();
		System.out.println("¬вед≥ть ≥ндекс кухн≥:");
		int index = scanner.nextInt();
		System.out.println("¬вед≥ть повний опис:");
		String fullDesc = scanner.next();
		System.out.println("¬вед≥ть назву страви:");
		String name = scanner.next();
		System.out.println("¬вед≥ть ц≥ну:");
		String price = scanner.next();
		System.out.println("¬вед≥ть короткий опис:");
		String shortDesc = scanner.next();
		System.out.println("¬вед≥ть вагу страви:");
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
		System.out.println("¬вед≥ть ≥ндекс страви:");
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
			System.out.println("ћеню пошуку: \n" 
					+ "ўоб шукати по назв≥ страви, введ≥ть: 1 \n"
					+ "ўоб шукати по початков≥й букв≥(чи п≥др€вку), введ≥ть: 2 \n"
					+ "ўоб шукати по назв≥ кухн≥, введ≥ть: 3 \n"
					+ "ўоб шукати по ц≥н≥, введ≥ть: 4 \n"
					+ "ўоб шукати по д≥апазону ц≥ни, введ≥ть: 5 \n"
					+ "ўоб вийти з меню пошуку, введ≥ть: 0");
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
				System.out.println("Ќев≥рний виб≥р! —пробуйте ще раз!");
			}
		}
	}
	
	private void findByName(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("¬вед≥ть назву страви:");
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
			System.out.println("Ќе знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findBySubLine(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("¬вед≥ть початкову букву чи п≥др€док:");
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
			System.out.println("Ќе знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findByCuisineName(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("¬вед≥ть назву кухн≥:");
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
			System.out.println("Ќе знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findByPrice(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("¬вед≥ть ц≥ну:");
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
			System.out.println("Ќе знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private void findByPriceRange(EntityManagerFactory factory) {
		int count = 0;
		System.out.println("¬вед≥ть м≥н≥мальну ц≥ну:");
		String minPrice = scanner.next();
		System.out.println("¬вед≥ть максимальну ц≥ну:");
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
			System.out.println("Ќе знайдено страв(и) з таким параметром!");
		}
		em.getTransaction().commit();
		em.close();
	}
}
