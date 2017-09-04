package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Place;
import ua.repository.PlaceRepository;

@Component
public class PlaceConveter implements Converter<Integer, Place>{
	
	private final PlaceRepository repository;

	public PlaceConveter(PlaceRepository repository) {
		this.repository = repository;
	}

	@Override
	public Place convert(Integer source) {
		return repository.findByNumber(source);
	}
	

}
