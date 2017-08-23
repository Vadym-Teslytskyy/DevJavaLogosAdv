package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.model.view.ComponentView;
import ua.repository.ComponentRepository;

@Component
public class ComponentViewConverter implements Converter<ComponentView, String>{
	
	@Override
	public String convert(ComponentView source) {
		return source.getAmount()+" "+source.getMs()+" "+source.getIngredient();
	}

	
	
	

}
