package it.energyservice.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import it.energyservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Page<Customer> findByAnnualTurnoverBetween(double from, double to, Pageable pageable);

	@Query("select c from Customer c where c.insertionDate between :from and :to")
	Page<Customer> getByInsertionDateBetween(@Param("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@Param("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Pageable pageable);
	
	@Query("select c from Customer c where c.lastContactDate between :from and :to")
	Page<Customer> getByLastContactDateBetween(@Param("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@Param("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Pageable pageable);

	Page<Customer> findByCompanyNameContains(String substring, Pageable pageable);
}
