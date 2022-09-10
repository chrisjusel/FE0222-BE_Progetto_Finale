package it.energyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.energyservice.model.Common;

@Repository
public interface CommonRepository extends JpaRepository<Common, Long>{

}
