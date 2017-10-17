package ua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.entity.Order;
import ua.entity.OrderStatus;
import ua.model.filter.OrderFilter;
import ua.model.request.OrderRequest;
import ua.service.OrderService;
import ua.service.PlaceService;

@Controller
@RequestMapping("/admin/order")
@SessionAttributes("order")
public class AdminOrderController {

	private final OrderService service;
	
	private final PlaceService placeService;

	@Autowired
	public AdminOrderController(OrderService service, PlaceService placeService) {
		this.service = service;
		this.placeService = placeService;
	}

	@ModelAttribute("order")
	public OrderRequest getForm() {
		return new OrderRequest();
	}
	
	@ModelAttribute("orderFilter")
	public OrderFilter getFilter(){
		return new OrderFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable,	@ModelAttribute("orderFilter") OrderFilter filter){
		model.addAttribute("statuses", service.findAllStatuses());
		model.addAttribute("meals", service.findAllMealsName());
		model.addAttribute("places", service.findAllPlacesView());
		model.addAttribute("orders", service.findAllOrders(pageable, filter));
		if (service.findAllOrders(pageable, filter).hasContent()||pageable.getPageNumber()==0)
			return "adminOrders";
		else
			return "redirect:/admin/order"+buildParams(pageable, filter);
	}
	
	@GetMapping("/updateStatus/{orderId}/{statusId}")
	public String update(@PathVariable Integer orderId, @PathVariable Integer statusId, Model model, @PageableDefault Pageable pageable,@ModelAttribute("orderFilter") OrderFilter filter) {
		Integer placeId = service.findOrderById(orderId).getPlace().getId();
		Integer userId = service.findOrderById(orderId).getPlace().getUserWhoReserved().getId();
		switch (statusId) {
		
		case 0:{
			service.updateStatus(orderId, OrderStatus.READY);
			}break;
			
		case 1:{
			service.updateStatus(orderId, OrderStatus.IN_PROGRES);
			}break;
		
		case 2:{
			service.updateStatus(orderId, OrderStatus.ACCEPTED);
			}break;
		
		case 3:{
			service.updateStatus(orderId, OrderStatus.IS_PAYED);
			}break;
		
		}
		List<Order> tableOrders = service.findOrderByPlaceId(placeId);
		boolean isNotPayed = false;
		for (Order order : tableOrders) {
			if (order.getStatus() != OrderStatus.IS_PAYED) {
				isNotPayed = true;
			}
		}
		if (isNotPayed == false) {
			placeService.cancelAllReservations(userId, placeId);
		}
		return "redirect:/admin/order"+buildParams(pageable, filter);
}
	
	private String buildParams(Pageable pageable, OrderFilter filter) {
		StringBuilder buffer = new StringBuilder();		
		buffer.append("?page=");
		if(!(service.findAllOrders(pageable, filter).hasContent())) 
			buffer.append(String.valueOf(pageable.getPageNumber()));
		else {
			buffer.append(String.valueOf(pageable.getPageNumber()+1));
		}
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		return buffer.toString();
}
	
	
}
