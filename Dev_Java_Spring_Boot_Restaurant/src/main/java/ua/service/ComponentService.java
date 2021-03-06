package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.SimpleFilter;
import ua.model.request.ComponentRequest;
import ua.model.view.ComponentView;

public interface ComponentService {
	
	List<String> findAllIngredients();

	List<String> findAllMss();

	List<ComponentView> findAllView();
	
	void save(ComponentRequest request);
	
	ComponentRequest findOneRequest(Integer id);
	
	void delete(Integer id);
	
	Page<ComponentView> findAllView(Pageable pageable);

	Page<ComponentView> findAllView(Pageable pageable, SimpleFilter filter);

}
