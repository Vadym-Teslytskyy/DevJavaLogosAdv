package ua.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Cuisine;

public interface CusineRepository extends JpaNameRepository<Cuisine>, JpaSpecificationExecutor<Cuisine>{

}
