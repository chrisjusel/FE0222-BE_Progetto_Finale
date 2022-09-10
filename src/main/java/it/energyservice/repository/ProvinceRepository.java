package it.energyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.energyservice.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

}
