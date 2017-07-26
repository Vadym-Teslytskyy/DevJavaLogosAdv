package ua.controller;

import java.math.BigDecimal;
import java.util.Scanner;

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
			System.out.println("���� ��: \n" 
					+ "��� ������ �����, ������: 1 \n"
					+ "��� ���������� ����� �� id, ������: 2(������ ������ ���� �� ������ ������, � ���� id(����� �����) ��� ���� ����������� �� ����); !���� ������ �������� ��� ��� ���� ���������� ���� \n"
					+ "��� �������� �����, ������: 3 \n"
					+ "��� ������ ������, ������: 4 \n"
					+ "��� ���������� ������ �� id, ������: 5 \n"
					+ "��� �������� ������ �� id, ������: 6 \n"
					+ "��� ����� � ��������, ������: 0");

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
				case "0":
					isRun = false;
					break;
				default:
					System.out.println("������� ����! ��������� �� ���!");
				}
			}
		
		factory.close();
	}
	
	private void addCuisien(EntityManagerFactory factory) {
		System.out.println("������ ����� ����:");
		String cuisineName = scanner.next();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Cuisine cuisine = new Cuisine(cuisineName);
		em.persist(cuisine);
		em.getTransaction().commit();
		em.close();
	}
	
	private void changeCuisien(EntityManagerFactory factory) {
		System.out.println("������ ������:");
		int index = scanner.nextInt();
		System.out.println("������ ���� ����� ����:");
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
		System.out.println("������ ������:");
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
		System.out.println("������ ������ ����:");
		int index = scanner.nextInt();
		System.out.println("������ ������ ����:");
		String fullDesc = scanner.next();
		System.out.println("������ ����� ������:");
		String name = scanner.next();
		System.out.println("������ ����:");
		String price = scanner.next();
		System.out.println("������ �������� ����:");
		String shortDesc = scanner.next();
		System.out.println("������ ���� ������:");
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
		System.out.println("������ ������ ������:");
		int indexMeal = scanner.nextInt();
		System.out.println("������ ������ ����:");
		int index = scanner.nextInt();
		System.out.println("������ ������ ����:");
		String fullDesc = scanner.next();
		System.out.println("������ ����� ������:");
		String name = scanner.next();
		System.out.println("������ ����:");
		String price = scanner.next();
		System.out.println("������ �������� ����:");
		String shortDesc = scanner.next();
		System.out.println("������ ���� ������:");
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
		System.out.println("������ ������ ������:");
		int indexMeal = scanner.nextInt();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Meal meal = em.find(Meal.class, indexMeal);
		em.persist(meal);
		em.remove(meal);
		em.getTransaction().commit();
		em.close();
	}
}
