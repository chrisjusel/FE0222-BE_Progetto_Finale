package it.energyservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.energyservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Page<Customer> findByfatturatoAnnualeBetween(double from, double to, Pageable pageable);
}
