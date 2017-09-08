package ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.Place;
import ua.model.filter.SimpleFilter;
import ua.model.request.PlaceRequest;
import ua.model.view.PlaceView;
import ua.repository.PlaceRepository;
import ua.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService{
	
	private final PlaceRepository repository;

	@Autowired
	public PlaceServiceImpl(PlaceRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(PlaceRequest request) {
		Place place = new Place();
		place.setId(request.getId());
		place.setCountofPeople(new Integer(request.getCountofPeople()));
		place.setNumber(new Integer(request.getNumber()));
		place.setIsFree(true);
		repository.save(place);
	}

	@Override
	public PlaceRequest findOneRequest(Integer id) {
		Place place = repository.findOneRequest(id);
		PlaceRequest request = new PlaceRequest();
		request.setCountofPeople(String.valueOf((place.getCountofPeople())));
		request.setId(place.getId());
		request.setNumber(String.valueOf((place.getNumber())));
		request.setIsFree(String.valueOf((place.getIsFree())));
		return request;
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public List<PlaceView> findAllView() {
		return repository.findAllView();
	}

	@Override
	public Page<PlaceView> findAllView(Pageable pageable) {
		return repository.findAllView(pageable);
	}

	@Override
	public Page<PlaceView> findAllView(Pageable pageable, SimpleFilter filter) {
		return repository.findAll(filter(filter), pageable);
	}
	
	private Specification<PlaceView> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}

}
