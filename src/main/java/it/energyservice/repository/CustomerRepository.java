package it.energyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.energyservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
