package it.energyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.energyservice.model.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long>{

}
