package ua.service.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.entity.Meal;
import ua.entity.Order;
import ua.entity.OrderStatus;
import ua.entity.Order_;
import ua.entity.Place;
import ua.model.filter.OrderFilter;

public class OrderFilterPredicateBuilder {

	private final CriteriaBuilder cb;
	
	private final Root<Order> root;
	
	private final OrderFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();

	public OrderFilterPredicateBuilder(CriteriaBuilder cb, Root<Order> root, OrderFilter filter) {
		this.cb = cb;
		this.root = root;
		this.filter = filter;
	}
	
	private void findByMealName() {
		if(!filter.getMealName().isEmpty()) {
			ListJoin<Order, Meal> join = root.join(Order_.meals);
			predicates.add(join.get("name").in(filter.getMealName()));
		}
	}
	private void findByPlaceNumber() {
		if(!filter.getPlaceNumber().isEmpty()) {
			Join<Order, Place> join = root.join(Order_.place);
			predicates.add(join.get("number").in(filter.getPlaceNumber()));
		}
	}
	private void findByStatus() {
		if(!filter.getStatus().isEmpty()) {
			predicates.add(root.get("status").in(OrderStatus.toOrderStatusConverter(filter.getStatus())));
		}
	}
	public Predicate toPredicate() {
		findByMealName();
		findByPlaceNumber();
		findByStatus();
		return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
	}
}
