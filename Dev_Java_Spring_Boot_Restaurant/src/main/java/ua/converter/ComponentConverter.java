package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.repository.ComponentRepository;

@Component
public class ComponentConverter implements Converter<Integer, ua.entity.Component>{
	
	private final ComponentRepository repository;

	public ComponentConverter(ComponentRepository repository) {
		this.repository = repository;
	}

	@Override
	public ua.entity.Component convert(Integer source) {
		return repository.findOne(source);
	}
	
	

}
