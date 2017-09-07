package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Ms;

public interface MsService extends CrudService<Ms, Integer>{

	Page<Ms> findAll(Pageable pageable);

}
