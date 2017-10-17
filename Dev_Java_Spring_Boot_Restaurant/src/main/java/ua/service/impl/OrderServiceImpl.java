package ua.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Meal;
import ua.entity.Order;
import ua.entity.OrderStatus;
import ua.entity.User;
import ua.model.filter.OrderFilter;
import ua.model.request.OrderRequest;
import ua.model.view.MealView;
import ua.model.view.OrderView;
import ua.model.view.PlaceView;
import ua.repository.OrderRepository;
import ua.repository.OrderViewRepository;
import ua.repository.UserRepository;
import ua.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderRepository repository;
	
	private final OrderViewRepository orderViewRepository;
	
	private final UserRepository userRepository;
	
	@Autowired
	public OrderServiceImpl(OrderRepository repository, UserRepository userRepository, OrderViewRepository orderViewRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
		this.orderViewRepository = orderViewRepository;
	}

	@Override
	public Page<OrderView> findAllView(Integer placeId, Pageable pageable) {
		Page<OrderView> ordersPage = repository.findAllView(placeId, pageable);
		for (OrderView orderView : ordersPage) {
			orderView.setMeals(findForOrder(orderView.getId()));
		}
		return ordersPage;
	}

	@Override
	public void saveOrder(OrderRequest request, User user) {
		request.setPlace(user.getPlace());
		request.setStatus(OrderStatus.ACCEPTED);
		Order order = new Order();
		order.setId(request.getId());
		order.setTime(LocalDateTime.now());
		order.setPlace(request.getPlace());
		order.setMeals(request.getMeals());
		order.setStatus(request.getStatus());
		
		List<Meal> tastedMeals = user.getTastedMeals();
		List<Meal> orderMeals = order.getMeals();
		for (Meal meal : orderMeals) {
			if (!tastedMeals.contains(meal))
				tastedMeals.add(meal);
		}		
		user.setTastedMeals(tastedMeals);
		userRepository.save(user);
		repository.save(order);
	}

	@Override
	public OrderRequest findOneRequest(Integer id) {
		Order order = repository.findOneRequest(id);
		OrderRequest request = new OrderRequest();
		request.setId(order.getId());
		request.setPlace(order.getPlace());
		request.setMeals(order.getMeals());
		request.setStatus(order.getStatus());
		return request;
	}

	@Override
	public List<String> findAllMealsName() {
		return repository.findAllMeals();
	}

	@Override
	public List<MealView> findForOrder(Integer orderId) {
		return repository.findForOrder(orderId);
	}

	@Override
	public Page<OrderView> findAllOrders(Pageable pageable, OrderFilter filter) {
		Page<OrderView> orderPage = orderViewRepository.findAllView(filter, pageable);
		for (OrderView orderView : orderPage) {
			orderView.setMeals(repository.findForOrder(orderView.getId()));
		}
		return orderPage;
	}

	@Override
	public Order findOrderById(Integer id) {
		return repository.findOrderById(id);
	}

	@Override
	public void updateStatus(Integer id, OrderStatus status) {
		Order order = repository.findOrderById(id);
		order.setStatus(status);
		repository.save(order);
	}

	@Override
	public List<Order> findOrderByPlaceId(Integer placeId) {
		return repository.findByPlaceId(placeId);
	}

	@Override
	public List<OrderStatus> findAllStatuses() {
		return repository.findAllStatuses();
	}

	@Override
	public List<PlaceView> findAllPlacesView() {
		return repository.findAllPlacesView();
	}

}
