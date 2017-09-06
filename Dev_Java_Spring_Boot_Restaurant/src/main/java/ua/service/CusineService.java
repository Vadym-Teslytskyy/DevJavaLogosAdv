package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Cuisine;

public interface CusineService extends CrudService<Cuisine, Integer>{

	Page<Cuisine> findAll(Pageable pageable);

}
