package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Ms;
import ua.repository.MsRepository;
import ua.service.MsService;

@Service
public class MsServiceImpl extends CrudServiceImpl<Ms, Integer> implements MsService{

	@Autowired
	public MsServiceImpl(MsRepository repository) {
		super(repository);
	}

}
