package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Place;
import ua.repository.PlaceRepository;
import ua.service.PlaceService;

@Service
public class PlaceServiceImpl extends CrudServiceImpl<Place, Integer> implements PlaceService{

	@Autowired
	public PlaceServiceImpl(PlaceRepository repository) {
		super(repository);
	}

}
