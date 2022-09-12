package it.energyservice.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.energyservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Page<Customer> findByAnnualTurnoverBetween(double from, double to, Pageable pageable);
	Page<Customer> findByInsertionDateBetween(Date from, Date to, Pageable pageable);
}
