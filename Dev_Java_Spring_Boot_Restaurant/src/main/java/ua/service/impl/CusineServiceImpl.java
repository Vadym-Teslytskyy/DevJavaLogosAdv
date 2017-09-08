package ua.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ua.entity.Cuisine;
import ua.model.filter.SimpleFilter;
import ua.repository.CusineRepository;
import ua.service.CusineService;

@Service
public class CusineServiceImpl extends CrudServiceImpl<Cuisine, Integer> implements CusineService{
	
	private final CusineRepository reposirory;
	
	public CusineServiceImpl(JpaRepository<Cuisine, Integer> repository, CusineRepository reposirory) {
		super(repository);
		this.reposirory = reposirory;
	}

	@Autowired
	public CusineServiceImpl(CusineRepository repository) {
		super(repository);
		this.reposirory = repository;
	}

	@Override
	public Page<Cuisine> findAll(Pageable pageable) {
		return reposirory.findAll(pageable);
	}

	@Override
	public Page<Cuisine> findAll(Pageable pageable, SimpleFilter filter) {
		return reposirory.findAll(filter(filter), pageable);
	}
	
	private Specification<Cuisine> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}

}
