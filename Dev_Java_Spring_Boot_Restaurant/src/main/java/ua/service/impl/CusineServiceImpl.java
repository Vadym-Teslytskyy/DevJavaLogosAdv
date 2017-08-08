package ua.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Cuisine;
import ua.repository.CusineRepository;
import ua.service.CusineService;

@Service
public class CusineServiceImpl extends CrudServiceImpl<Cuisine, Integer> implements CusineService{

	@Autowired
	public CusineServiceImpl(CusineRepository repository) {
		super(repository);
	}

}
