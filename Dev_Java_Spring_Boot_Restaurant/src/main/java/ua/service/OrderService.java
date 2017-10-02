package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.User;
import ua.model.request.OrderRequest;
import ua.model.view.MealView;
import ua.model.view.OrderView;

public interface OrderService{

	Page<OrderView> findAllView(Integer placeId, Pageable pageable);

	void saveOrder(OrderRequest request, User user);
	
	OrderRequest findOneRequest(Integer id);
	
	List<String> findAllMealsName();
	
	List<MealView> findForOrder(Integer orderId);
	
}
