package ua.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Ms;

public interface MsRepository extends JpaNameRepository<Ms>, JpaSpecificationExecutor<Ms>{

}
