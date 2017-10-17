package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Order;
import ua.entity.OrderStatus;
import ua.entity.User;
import ua.model.filter.OrderFilter;
import ua.model.request.OrderRequest;
import ua.model.view.MealView;
import ua.model.view.OrderView;
import ua.model.view.PlaceView;

public interface OrderService{

	Page<OrderView> findAllView(Integer placeId, Pageable pageable);

	Page<OrderView> findAllOrders(Pageable pageable, OrderFilter filter);
	
	void saveOrder(OrderRequest request, User user);
	
	OrderRequest findOneRequest(Integer id);
	
	List<String> findAllMealsName();
	
	List<PlaceView> findAllPlacesView();
	
	List<MealView> findForOrder(Integer orderId);

	Order findOrderById(Integer id);

	void updateStatus(Integer id, OrderStatus status);

	List<Order> findOrderByPlaceId(Integer placeId);
	
	List<OrderStatus> findAllStatuses();
	
}
