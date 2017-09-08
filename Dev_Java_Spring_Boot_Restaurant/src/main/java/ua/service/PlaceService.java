package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.SimpleFilter;
import ua.model.request.PlaceRequest;
import ua.model.view.PlaceView;

public interface PlaceService {

	List<PlaceView> findAllView();
	
	void save(PlaceRequest request);
	
	PlaceRequest findOneRequest(Integer id);
	
	void delete(Integer id);

	Page<PlaceView> findAllView(Pageable pageable);

	Page<PlaceView> findAllView(Pageable pageable, SimpleFilter filter);
}
