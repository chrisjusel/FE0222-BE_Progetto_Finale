package it.energyservice.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import it.energyservice.model.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {

	@Query("select billings from Customer c join c.billings billings where c.id = :customerId")
	Page<Billing> getBillingsByCustomerId(@Param("customerId") Long id, Pageable pageable);

	@Query("select billing from Billing billing where billing.state.id = :billingState")
	Page<Billing> getBillingsByBillingState(@Param("billingState") Long id, Pageable pageable);

	@Query("select billing from Billing billing where billing.date between :date and :to  ")
	Page<Billing> getByDate(@Param("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@Param("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Pageable pageable);

	Page<Billing> findByYear(Integer year, Pageable pageable);

	Page<Billing> findByAmountBetween(Double from, Double to, Pageable pageable);
}
