package ua.repository.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ua.entity.Order;
import ua.entity.Order_;
import ua.model.filter.OrderFilter;
import ua.model.view.OrderView;
import ua.repository.OrderViewRepository;
import ua.service.classes.OrderFilterPredicateBuilder;

@Repository
public class OrderViewRepositoryImpl implements OrderViewRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<OrderView> findAllView(OrderFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderView> cq = cb.createQuery(OrderView.class);
		Root<Order> root = cq.from(Order.class);
		cq.multiselect(root.get(Order_.id), root.get(Order_.place), root.get(Order_.status), root.get(Order_.time));
		Predicate predicate = new OrderFilterPredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
				cq.orderBy(toOrders(pageable.getSort(), root, cb));
				List<OrderView> content = em.createQuery(cq)
						.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
						.setMaxResults(pageable.getPageSize())
						.getResultList();
				CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
				Root<Order> countRoot = countQuery.from(Order.class);
				countQuery.select(cb.count(countRoot));
				Predicate countPredicate = new OrderFilterPredicateBuilder(cb, countRoot, filter).toPredicate();
				if(countPredicate!=null) countQuery.where(countPredicate);
				return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}

}
