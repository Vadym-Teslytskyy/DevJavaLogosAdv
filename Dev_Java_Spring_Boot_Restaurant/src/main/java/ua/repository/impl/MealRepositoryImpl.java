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

import ua.entity.Meal;
import ua.entity.Meal_;
import ua.model.filter.MealFilter;
import ua.model.view.MealIndexView;
import ua.repository.MealViewRepository;
import ua.service.classes.MealFilterPredicateBuilder;

@Repository
public class MealRepositoryImpl implements MealViewRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<MealIndexView> findAll(MealFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MealIndexView> cq = cb.createQuery(MealIndexView.class);
		Root<Meal> root = cq.from(Meal.class);
		cq.multiselect(root.get(Meal_.id), root.get(Meal_.photoUrl), root.get(Meal_.version), root.get(Meal_.rate), root.get(Meal_.name), root.get(Meal_.shortDescription));
		Predicate predicate = new MealFilterPredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
				cq.orderBy(toOrders(pageable.getSort(), root, cb));
				List<MealIndexView> content = em.createQuery(cq)
						.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
						.setMaxResults(pageable.getPageSize())
						.getResultList();
				CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
				Root<Meal> countRoot = countQuery.from(Meal.class);
				countQuery.select(cb.count(countRoot));
				Predicate countPredicate = new MealFilterPredicateBuilder(cb, countRoot, filter).toPredicate();
				if(countPredicate!=null) countQuery.where(countPredicate);
				return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}
	
}
