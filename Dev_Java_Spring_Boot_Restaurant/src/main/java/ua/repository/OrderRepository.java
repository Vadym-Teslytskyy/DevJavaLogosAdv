package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Order;
import ua.entity.OrderStatus;
import ua.model.view.MealView;
import ua.model.view.OrderView;
import ua.model.view.PlaceView;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<OrderView>{
	
	@Query("SELECT m.name FROM Meal m")
	List<String> findAllMeals();
	
	@Query("SELECT new ua.model.view.PlaceView(p.id, p.countofPeople, p.number, p.isFree) FROM Place p ORDER BY number")
	List<PlaceView> findAllPlacesView();
	
	@Query(value="SELECT new ua.model.view.OrderView(o.id, o.place, o.status, o.time) FROM Order o JOIN o.place p WHERE p.id=?1 AND (NOT (o.status=3) OR o.status=null) ORDER BY o.status DESC",
			countQuery="SELECT count(o.id) FROM Order o JOIN o.place p WHERE p.id=?1 AND (NOT (o.status=3) OR o.status=null) ORDER BY o.status DESC")
	Page<OrderView> findAllView(Integer placeId, Pageable pageable);
	
	@Query(value="SELECT new ua.model.view.OrderView(o.id, o.place, o.status, o.time) FROM Order o WHERE o.status!=3 OR o.status!=null ORDER BY o.status DESC",
			countQuery="SELECT count(o.id) FROM Order o")
	Page<OrderView> findAllOrders(Pageable pageable);
	
	@Query("SELECT o FROM Order o JOIN FETCH o.place WHERE o.id=?1")
	Order findOneRequest(Integer id);
	
	@Query("SELECT o FROM Order o WHERE o.id=?1")
	Order findOrderById(Integer id);
	
	@Query("SELECT o FROM Order o JOIN o.place p WHERE p.id=?1")
	List<Order> findByPlaceId(Integer id);
	
	@Query("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.rate, m.name, m.fullDescription, m.price, m.weight, c.name) FROM Meal m JOIN m.cuisine c JOIN m.orders o WHERE o.id=?1")
	List<MealView> findForOrder(Integer orderId);
	
	@Query("SELECT distinct o.status FROM Order o")
	List<OrderStatus> findAllStatuses();

}
