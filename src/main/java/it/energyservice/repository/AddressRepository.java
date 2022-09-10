package it.energyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.energyservice.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
